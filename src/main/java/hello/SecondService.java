package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint8;
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

import static org.web3j.tx.Contract.GAS_LIMIT;
import static org.web3j.tx.ManagedTransaction.GAS_PRICE;

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

    public Credentials getCredentials() {

        System.out.println(GlobalInfo.getInstance());
        try {
            return WalletUtils.loadCredentials(
                    GlobalInfo.getInstance().getWalletPwd(),
                    GlobalInfo.getInstance().getWalletFile()
            );

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deployContranct()  {
        SocialRecord contract = null;
        Long time = 0L;
        try {
            // Start deployment
            Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());

            contract = SocialRecord.deploy(web3j, this.getCredentials(), GAS_PRICE, GAS_LIMIT, BigInteger.valueOf(0)).get();

            // End deployment
            Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());

            // Set the contract globally
            GlobalInfo.getInstance().setContract(contract);

            // Calculate the time span
            time = (timestamp2.getTime()-timestamp1.getTime())/1000;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        String ret = "{'deploying_time': "+time+" s, 'address': "+contract.getContractAddress()+"}";
        return ret;
    }

    public String sendRawTransaction(String rrr) {
        //RawTransaction rawTransaction  = RawTransaction.createEtherTransaction(new BigInteger(String.valueOf("108")),Contract.GAS_PRICE, Contract.GAS_LIMIT,"0x17b507dFA41656c9205b354FA69Fa292CD9FC702", new BigInteger(String.valueOf("1"));

        //byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, this.getCredentials());
        //String hexValue = Numeric.toHexString(signedMessage);
        System.out.println("sendRawTransaction function");
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

    public String verify(Verify obj){
        Address val = null;
        Utf8String msg = new Utf8String(obj.msg);
        Utf8String r = new Utf8String(obj.r);
        Utf8String s = new Utf8String(obj.s);
        Uint8 v = new Uint8(obj.v);

        try {
            val = ((SocialRecord) GlobalInfo
                    .getInstance()
                    .getContract())
                    .verify(msg, v, r, s)
                    .get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return val.toString();
    }

    public void addSocialRecord(String globalID) {
        ((SocialRecord) GlobalInfo.getInstance().getContract()).addSocialRecord( new Utf8String(globalID), new Utf8String("Random Content... LOOOOOL"));
    }

    public String getSocialRecord(String globalID) {
        SocialRecord contract = SocialRecord.load(
                "0x68d2300251f63e1f0d3fb1c6c83cff9cb97f129d", web3j, this.getCredentials(), GAS_PRICE, GAS_LIMIT);
        System.out.println(globalID);
        String val = null;
        try {
            val = contract.getSocialRecord(new Utf8String(globalID)).get().getValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return val;

    }
}
