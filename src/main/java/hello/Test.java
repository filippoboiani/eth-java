package hello;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.2.1.
 */
public final class Test extends Contract {
    private static final String BINARY = "6060604052341561000c57fe5b5b61010b8061001c6000396000f300606060405263ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663184c69658114604d57806354ccd77714606c5780638749034e146095575bfe5b3415605457fe5b605a60b6565b60408051918252519081900360200190f35b3415607357fe5b6081600435602435151560bc565b604080519115158252519081900360200190f35b3415609c57fe5b608160d6565b604080519115158252519081900360200190f35b60005481565b60008290556001805460ff19168215151781555b92915050565b60015460ff16815600a165627a7a7230582072e0a9093692bad11bb04737c98c19f4708d191a39f32df95e4ac7a542816b530029";

    private Test(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private Test(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public Future<Uint256> uno() {
        Function function = new Function("uno", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> test(Uint256 _uno, Bool _due) {
        Function function = new Function("test", Arrays.<Type>asList(_uno, _due), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Bool> due() {
        Function function = new Function("due", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<Test> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(Test.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<Test> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(Test.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Test load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Test(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Test load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Test(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
