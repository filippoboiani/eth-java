package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.RawTransaction;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.tx.Contract;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Timestamp;
import java.util.concurrent.ExecutionException;

/**
 * Created by filippoboiani on 25/06/2017.
 */
@Service
public class SecondService {

    @Autowired
    private Web3j web3j;

    public String getClientVersion() {
        Web3ClientVersion web3ClientVersion = null;
        try {
            web3ClientVersion = web3j.web3ClientVersion().send();
            return web3ClientVersion.getWeb3ClientVersion();
        } catch (IOException e) {
            e.printStackTrace();
            return "An error occurred while attempting to connect to Web3j...";
        }

    }

    public String sendRawTransaction(String rrr) {
        //RawTransaction rawTransaction  = RawTransaction.createEtherTransaction(new BigInteger(String.valueOf("108")),Contract.GAS_PRICE, Contract.GAS_LIMIT,"0x17b507dFA41656c9205b354FA69Fa292CD9FC702", new BigInteger(String.valueOf("1"));

        //byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, this.getCredentials());
        //String hexValue = Numeric.toHexString(signedMessage);
        System.out.println("sendRawTransactino function");
        EthSendTransaction ethSendTransaction = null;
        try {
            ethSendTransaction = web3j.ethSendRawTransaction(rrr).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        String transactionHash = ethSendTransaction.getTransactionHash();
        System.out.println(transactionHash);
        // poll for transaction response via org.web3j.protocol.Web3j.ethGetTransactionReceipt(<txHash>)
        return transactionHash;
    }
}
