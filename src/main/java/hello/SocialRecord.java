package hello;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.2.1.
 */
public final class SocialRecord extends Contract {
    private static final String BINARY = "606060405234156200000d57fe5b5b5b60008054600160a060020a03191633600160a060020a03161790555b6040805180820190915260128082527f48656c6c6f206e657720636f6e747261637400000000000000000000000000006020909201918252620000719160029162000105565b506040805160808101825260078183019081527f43726561746f72000000000000000000000000000000000000000000000000006060830152815260016020808301829052600160a060020a03331660009081529181529290208151805192939192620000e2928492019062000105565b50602091909101516001909101805460ff19169115159190911790555b62000235565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200014857805160ff191683800117855562000178565b8280016001018555821562000178579182015b82811115620001785782518255916020019190600101906200015b565b5b506200018792915062000211565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200014857805160ff191683800117855562000178565b8280016001018555821562000178579182015b82811115620001785782518255916020019190600101906200015b565b5b506200018792915062000211565b5090565b6200023291905b8082111562000187576000815560010162000218565b5090565b90565b610d6380620002456000396000f300606060405236156100805763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663368b8772811461008257806340d17c7a1461015857806341c0e1b51461026b5780636383cc201461027d5780636f77926b14610390578063e21f37ce1461042c578063f3848cf3146104bc575bfe5b341561008a57fe5b6100d8600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284375094965061059295505050505050565b60408051602080825283518183015283519192839290830191850190808383821561011e575b80518252602083111561011e57601f1990920191602091820191016100fe565b505050905090810190601f16801561014a5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561016057fe5b6100d8600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284375050604080516020601f89358b018035918201839004830284018301909452808352979998810197919650918201945092508291508401838280828437509496506105d195505050505050565b60408051602080825283518183015283519192839290830191850190808383821561011e575b80518252602083111561011e57601f1990920191602091820191016100fe565b505050905090810190601f16801561014a5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561027357fe5b61027b610766565b005b341561028557fe5b6100d8600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284375050604080516020601f89358b0180359182018390048302840183019094528083529799988101979196509182019450925082915084018382808284375094965061079395505050505050565b60408051602080825283518183015283519192839290830191850190808383821561011e575b80518252602083111561011e57601f1990920191602091820191016100fe565b505050905090810190601f16801561014a5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561039857fe5b6100d8600160a060020a036004351661097f565b60408051602080825283518183015283519192839290830191850190808383821561011e575b80518252602083111561011e57601f1990920191602091820191016100fe565b505050905090810190601f16801561014a5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561043457fe5b6100d8610a58565b60408051602080825283518183015283519192839290830191850190808383821561011e575b80518252602083111561011e57601f1990920191602091820191016100fe565b505050905090810190601f16801561014a5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156104c457fe5b6100d8600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843750949650610ae395505050505050565b60408051602080825283518183015283519192839290830191850190808383821561011e575b80518252602083111561011e57601f1990920191602091820191016100fe565b505050905090810190601f16801561014a5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b61059a610c06565b60005433600160a060020a039081169116146105b65760006000fd5b81516105c9906002906020850190610c18565b505b5b919050565b6105d9610c06565b600160a060020a0333166000908152600160208190526040909120015460ff161561075e57604060405190810160405280838152602001600115158152506003846040518082805190602001908083835b602083106106495780518252601f19909201916020918201910161062a565b51815160209384036101000a600019018019909216911617905292019485525060405193849003810190932084518051919461068a94508593500190610c18565b50602091820151600191909101805460ff19169115159190911790556040805133600160a060020a038116825281840183815286519383019390935285517f7cea05059c5cf78518a4700eb575eb4b9f85dd99216101771c716e1649da2ce0949193879392909160608401918501908083838215610723575b80518252602083111561072357601f199092019160209182019101610703565b505050905090810190601f16801561074f5780820380516001836020036101000a031916815260200191505b50935050505060405180910390a15b5b5b92915050565b60005433600160a060020a039081169116146107825760006000fd5b600054600160a060020a0316ff5b5b565b61079b610c06565b600160a060020a0333166000908152600160208190526040909120015460ff16151561075e57604060405190810160405280838152602001600115158152506003846040518082805190602001908083835b6020831061080c5780518252601f1990920191602091820191016107ed565b51815160209384036101000a600019018019909216911617905292019485525060405193849003810190932084518051919461084d94508593500190610c18565b506020918201516001918201805460ff191691151591909117905560408051808201825286815280840183905233600160a060020a03166000908152928452912081518051929391926108a39284920190610c18565b50602091820151600191909101805460ff19169115159190911790556040805133600160a060020a038116825281840183815286519383019390935285517fe722fb3bbd52a1daf5396017091eb309257265547a713f85be83e473c9aff741949193879392909160608401918501908083838215610723575b80518252602083111561072357601f199092019160209182019101610703565b505050905090810190601f16801561074f5780820380516001836020036101000a031916815260200191505b50935050505060405180910390a15b5b5b92915050565b610987610c06565b600160a060020a0333166000908152600160208190526040909120015460ff16156105cb57600160a060020a03821660009081526001602081815260409283902080548451600294821615610100026000190190911693909304601f8101839004830284018301909452838352919290830182828015610a485780601f10610a1d57610100808354040283529160200191610a48565b820191906000526020600020905b815481529060010190602001808311610a2b57829003601f168201915b50939450505050505b5b5b919050565b6002805460408051602060018416156101000260001901909316849004601f81018490048402820184019092528181529291830182828015610adb5780601f10610ab057610100808354040283529160200191610adb565b820191906000526020600020905b815481529060010190602001808311610abe57829003601f168201915b505050505081565b610aeb610c06565b600160a060020a0333166000908152600160208190526040909120015460ff16156105cb576003826040518082805190602001908083835b60208310610b425780518252601f199092019160209182019101610b23565b518151600019602094850361010090810a820192831692199390931691909117909252949092019687526040805197889003820188208054601f6002600183161590980290950116959095049283018290048202880182019052818752929450925050830182828015610a485780601f10610a1d57610100808354040283529160200191610a48565b820191906000526020600020905b815481529060010190602001808311610a2b57829003601f168201915b50939450505050505b5b5b919050565b60408051602081019091526000815290565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610c5957805160ff1916838001178555610c86565b82800160010185558215610c86579182015b82811115610c86578251825591602001919060010190610c6b565b5b50610c93929150610d16565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610c5957805160ff1916838001178555610c86565b82800160010185558215610c86579182015b82811115610c86578251825591602001919060010190610c6b565b5b50610c93929150610d16565b5090565b610d3491905b80821115610c935760008155600101610d1c565b5090565b905600a165627a7a7230582085a386bc1cfa12d9dbdd71b13398fdafeeb3bc9b1d377fc9dad8b89c44e1504f0029";

    private SocialRecord(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private SocialRecord(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<SocialRecordAddedEventResponse> getSocialRecordAddedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("SocialRecordAdded", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<SocialRecordAddedEventResponse> responses = new ArrayList<SocialRecordAddedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            SocialRecordAddedEventResponse typedResponse = new SocialRecordAddedEventResponse();
            typedResponse.user = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.socialRecordHash = (Utf8String) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SocialRecordAddedEventResponse> socialRecordAddedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("SocialRecordAdded", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, SocialRecordAddedEventResponse>() {
            @Override
            public SocialRecordAddedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                SocialRecordAddedEventResponse typedResponse = new SocialRecordAddedEventResponse();
                typedResponse.user = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.socialRecordHash = (Utf8String) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public List<SocialRecordUpdatedEventResponse> getSocialRecordUpdatedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("SocialRecordUpdated", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<SocialRecordUpdatedEventResponse> responses = new ArrayList<SocialRecordUpdatedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            SocialRecordUpdatedEventResponse typedResponse = new SocialRecordUpdatedEventResponse();
            typedResponse.updater = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.socialRecordHash = (Utf8String) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SocialRecordUpdatedEventResponse> socialRecordUpdatedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("SocialRecordUpdated", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, SocialRecordUpdatedEventResponse>() {
            @Override
            public SocialRecordUpdatedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                SocialRecordUpdatedEventResponse typedResponse = new SocialRecordUpdatedEventResponse();
                typedResponse.updater = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.socialRecordHash = (Utf8String) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Future<TransactionReceipt> setMessage(Utf8String theMessage) {
        Function function = new Function("setMessage", Arrays.<Type>asList(theMessage), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> updateSocialRecordHash(Utf8String globalId, Utf8String socialRecordHash) {
        Function function = new Function("updateSocialRecordHash", Arrays.<Type>asList(globalId, socialRecordHash), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> kill() {
        Function function = new Function("kill", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> addSocialRecord(Utf8String globalId, Utf8String socialRecordHash) {
        Function function = new Function("addSocialRecord", Arrays.<Type>asList(globalId, socialRecordHash), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Utf8String> getUser(Address _user) {
        Function function = new Function("getUser", 
                Arrays.<Type>asList(_user), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> message() {
        Function function = new Function("message", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> getSocialRecordHash(Utf8String globalId) {
        Function function = new Function("getSocialRecordHash", 
                Arrays.<Type>asList(globalId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<SocialRecord> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(SocialRecord.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<SocialRecord> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(SocialRecord.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static SocialRecord load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialRecord(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static SocialRecord load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialRecord(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class SocialRecordAddedEventResponse {
        public Address user;

        public Utf8String socialRecordHash;
    }

    public static class SocialRecordUpdatedEventResponse {
        public Address updater;

        public Utf8String socialRecordHash;
    }
}