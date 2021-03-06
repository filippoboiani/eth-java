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
import org.web3j.abi.datatypes.generated.Uint8;
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
    private static final String BINARY = "6060604052341561000c57fe5b5b60008054600160a060020a03191633600160a060020a031690811782556040805160808101825260078183019081527f43726561746f720000000000000000000000000000000000000000000000000060608301528152600160208083018290529385528352922082518051919261008a928492909101906100ac565b50602091909101516001909101805460ff19169115159190911790555b61014c565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100ed57805160ff191683800117855561011a565b8280016001018555821561011a579182015b8281111561011a5782518255916020019190600101906100ff565b5b5061012792915061012b565b5090565b61014991905b808211156101275760008155600101610131565b5090565b90565b610d408061015b6000396000f300606060405236156100755763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166341c0e1b581146100775780634b733d0f146100895780636383cc201461017d57806393a1122e1461029c578063c57411a814610306578063d3b493b314610425575bfe5b341561007f57fe5b6100876104fb565b005b341561009157fe5b610161600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284375050604080516020601f818a01358b0180359182018390048302840183018552818452989a60ff8b35169a90999401975091955091820193509150819084018382808284375050604080516020601f89358b0180359182018390048302840183019094528083529799988101979196509182019450925082915084018382808284375094965061052895505050505050565b60408051600160a060020a039092168252519081900360200190f35b341561018557fe5b610210600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284375050604080516020601f89358b018035918201839004830284018301909452808352979998810197919650918201945092508291508401838280828437509496506105a595505050505050565b6040805183151581526020808201838152845193830193909352835191929160608401918501908083838215610261575b80518252602083111561026157601f199092019160209182019101610241565b505050905090810190601f16801561028d5780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b34156102a457fe5b6102f2600480803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437509496506107f795505050505050565b604080519115158252519081900360200190f35b341561030e57fe5b610210600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284375050604080516020601f89358b018035918201839004830284018301909452808352979998810197919650918201945092508291508401838280828437509496506108ae95505050505050565b6040805183151581526020808201838152845193830193909352835191929160608401918501908083838215610261575b80518252602083111561026157601f199092019160209182019101610241565b505050905090810190601f16801561028d5780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b341561042d57fe5b61047b600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843750949650610aab95505050505050565b6040805160208082528351818301528351919283929083019185019080838382156104c1575b8051825260208311156104c157601f1990920191602091820191016104a1565b505050905090810190601f1680156104ed5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b60005433600160a060020a039081169116146105175760006000fd5b600054600160a060020a0316ff5b5b565b60208481015183820151838301516040805160008181528187018352908201819052815185815260ff8a16818801528083018590526060810184905291519095600192608080820193601f1981019281900390910190898661646e5a03f1151561058e57fe5b50506020604051035193505b505050949350505050565b60006105af610c1a565b6002846040518082805190602001908083835b602083106105e15780518252601f1990920191602091820191016105c2565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092206001015460ff16159150610634905057505060408051602081019091526000808252906107f0565b604060405190810160405280848152602001600115158152506002856040518082805190602001908083835b6020831061067f5780518252601f199092019160209182019101610660565b51815160209384036101000a60001901801990921691161790529201948552506040519384900381019093208451805191946106c094508593500190610c2c565b506020918201516001918201805460ff191691151591909117905560408051808201825287815280840183905233600160a060020a03166000908152928452912081518051929391926107169284920190610c2c565b50602091820151600191909101805460ff19169115159190911790556040805133600160a060020a038116825281840183815288519383019390935287517fe722fb3bbd52a1daf5396017091eb309257265547a713f85be83e473c9aff7419491938993929091606084019185019080838382156107af575b8051825260208311156107af57601f19909201916020918201910161078f565b505050905090810190601f1680156107db5780820380516001836020036101000a031916815260200191505b50935050505060405180910390a15060019050815b9250929050565b60006002826040518082805190602001908083835b6020831061082b5780518252601f19909201916020918201910161080c565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092209150600090506108668282610cab565b506001908101805460ff19169055600160a060020a033316600090815260209190915260408120906108988282610cab565b506001908101805460ff1916905590505b919050565b60006108b8610c1a565b6002846040518082805190602001908083835b602083106108ea5780518252601f1990920191602091820191016108cb565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092206001015460ff161515915061093e905057505060408051602081019091526000808252906107f0565b604060405190810160405280848152602001600115158152506002856040518082805190602001908083835b602083106109895780518252601f19909201916020918201910161096a565b51815160209384036101000a60001901801990921691161790529201948552506040519384900381019093208451805191946109ca94508593500190610c2c565b50602091820151600191909101805460ff19169115159190911790556040805133600160a060020a038116825281840183815288519383019390935287517f7cea05059c5cf78518a4700eb575eb4b9f85dd99216101771c716e1649da2ce09491938993929091606084019185019080838382156107af575b8051825260208311156107af57601f19909201916020918201910161078f565b505050905090810190601f1680156107db5780820380516001836020036101000a031916815260200191505b50935050505060405180910390a15060019050815b9250929050565b610ab3610c1a565b6002826040518082805190602001908083835b60208310610ae55780518252601f199092019160209182019101610ac6565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092206001015460ff1615159150610b2790505760006000fd5b6002826040518082805190602001908083835b60208310610b595780518252601f199092019160209182019101610b3a565b518151600019602094850361010090810a820192831692199390931691909117909252949092019687526040805197889003820188208054601f6002600183161590980290950116959095049283018290048202880182019052818752929450925050830182828015610c0d5780601f10610be257610100808354040283529160200191610c0d565b820191906000526020600020905b815481529060010190602001808311610bf057829003601f168201915b505050505090505b919050565b60408051602081019091526000815290565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610c6d57805160ff1916838001178555610c9a565b82800160010185558215610c9a579182015b82811115610c9a578251825591602001919060010190610c7f565b5b50610ca7929150610cf3565b5090565b50805460018160011615610100020316600290046000825580601f10610cd15750610cef565b601f016020900490600052602060002090810190610cef9190610cf3565b5b50565b610d1191905b80821115610ca75760008155600101610cf9565b5090565b905600a165627a7a72305820905575d822d16fbf6359cc87b8f7a75f7bc5cc3b89f4d07aed45048c8b5275210029";

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
            typedResponse.globalId = (Utf8String) eventValues.getNonIndexedValues().get(1);
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
                typedResponse.globalId = (Utf8String) eventValues.getNonIndexedValues().get(1);
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
            typedResponse.globalId = (Utf8String) eventValues.getNonIndexedValues().get(1);
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
                typedResponse.globalId = (Utf8String) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public List<SocialReocordDeletedEventResponse> getSocialReocordDeletedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("SocialReocordDeleted", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<SocialReocordDeletedEventResponse> responses = new ArrayList<SocialReocordDeletedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            SocialReocordDeletedEventResponse typedResponse = new SocialReocordDeletedEventResponse();
            typedResponse.deleter = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.globalId = (Utf8String) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SocialReocordDeletedEventResponse> socialReocordDeletedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("SocialReocordDeleted", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, SocialReocordDeletedEventResponse>() {
            @Override
            public SocialReocordDeletedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                SocialReocordDeletedEventResponse typedResponse = new SocialReocordDeletedEventResponse();
                typedResponse.deleter = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.globalId = (Utf8String) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Future<TransactionReceipt> kill() {
        Function function = new Function("kill", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Address> verify(Utf8String _hash, Uint8 _v, Utf8String _r, Utf8String _s) {
        Function function = new Function("verify", 
                Arrays.<Type>asList(_hash, _v, _r, _s), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> addSocialRecord(Utf8String _globalId, Utf8String _socialRecordString) {
        Function function = new Function("addSocialRecord", Arrays.<Type>asList(_globalId, _socialRecordString), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> deleteSocialRecord(Utf8String _globalId) {
        Function function = new Function("deleteSocialRecord", Arrays.<Type>asList(_globalId), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> updateSocialRecord(Utf8String _globalId, Utf8String _socialRecordString) {
        Function function = new Function("updateSocialRecord", Arrays.<Type>asList(_globalId, _socialRecordString), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Utf8String> getSocialRecord(Utf8String _globalId) {
        Function function = new Function("getSocialRecord", 
                Arrays.<Type>asList(_globalId), 
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

        public Utf8String globalId;
    }

    public static class SocialRecordUpdatedEventResponse {
        public Address updater;

        public Utf8String globalId;
    }

    public static class SocialReocordDeletedEventResponse {
        public Address deleter;

        public Utf8String globalId;
    }
}
