pragma solidity ^0.4.0;

// solc FirstContract.sol --bin --abi --optimize -o ./
// web3j solidity generate /path/to/<smart-contract>.bin /path/to/<smart-contract>.abi -o /path/to/src/main/java -p com.your.organisation.name
// Inside the resource folder
// web3j solidity generate FirstContract.bin FirstContract.abi -o ../ -p hello

// Owning and modifying the contract
contract Owned {

    struct User {
        string globalId;
        bool exists;
    }

    address owner;
    mapping (address => User) users;

    function Owned() { owner = msg.sender; }

    // Only the owner can call the function
    modifier onlyOwner {
        require(msg.sender == owner);
        _;
    }

    // Only saved users can call the function
    modifier onlyUser {
        if(users[msg.sender].exists) {
            _;
        }
    }

    modifier onlyNotUser {
       if(!users[msg.sender].exists) {
            _;
        }
    }
}

// Killing the contract
contract Mortal is Owned {

    // Kill the contract (only the owner can)
    function kill() onlyOwner {
        selfdestruct(owner);
    }
}

// SocialRecord contract must be mortal and owned
contract SocialRecord is Mortal {

    struct SR {
        string socialRecordHash;
        bool exists;
    }

    // State variable
    string public message;
    mapping (string => SR) private globalIds;

    // Constructor
    function SocialRecord(){
        message = "Hello new contract";
        users[msg.sender] = User("Creator", true);
    }

    // Events
    event SocialRecordAdded(address user, string socialRecordHash);
    event SocialRecordUpdated(address updater, string socialRecordHash);

    // Functions
    // function addUser(address userAddress, string name) onlyOwner returns (string){
    //     users[userAddress] = name;
    // }

    function addSocialRecord(string globalId, string socialRecordHash) onlyNotUser returns (string){
        globalIds[globalId] = SR(socialRecordHash, true);
        users[msg.sender] = User(globalId, true);
        SocialRecordAdded(msg.sender, socialRecordHash);
    }

    function getSocialRecordHash(string globalId) onlyUser constant returns (string socialRecordHash){
        socialRecordHash = globalIds[globalId].socialRecordHash;
        return socialRecordHash;
    }

    function updateSocialRecordHash(string globalId, string socialRecordHash) onlyUser returns (string){
        globalIds[globalId] = SR(socialRecordHash, true);
        // Trigger the event
        SocialRecordUpdated(msg.sender, socialRecordHash);
    }

    function setMessage(string theMessage) onlyOwner returns (string) {
        message = theMessage;
    }

    function getUser(address _user) onlyUser constant returns (string _theUser){
        _theUser = users[_user].globalId;
        return _theUser;
    }
}