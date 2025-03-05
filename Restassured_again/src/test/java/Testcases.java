import models.blockchain.BlockInformation;
import utils.RetryAnalyzer;
import utils.RetryListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.Endpoints;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.Response;
import models.blockchain.Transactions;
import org.testng.Assert;
import org.testng.annotations.*;
import services.APIServices;
import java.util.List;

@Listeners(RetryListener.class)
public class Testcases {

    public static final int block_height = 680000;
    public static final int expectedTransactionsCount = 2875;
    public static final String txid_1 = "96d92f03000f625a38bf8cb91c01188a02b7972238cc6c4e0c6f334cf755004d";
    public static final String txid_2 = "6dd68336c085d5b7b694e2bf6f6c11bca589aea07b6f1c0232bd627c3d217074";

    public static String block_hash;

    @BeforeMethod(alwaysRun = true)
    public void getBlockHash() {
        APIServices.setRestAssuredRequestResource("BLOCK_STREAM_REQUEST_URL");
        String get_blockHash_endpoint = Endpoints.BLOCK_HASH + block_height;
        Response get_blockHash_response = APIServices.get(get_blockHash_endpoint);

        block_hash = get_blockHash_response.getBody().asString();
        System.out.println("Block hash = " + block_hash);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void scenario1_assertTransactions() throws JsonPathException, JsonProcessingException {

        String get_blockInformation_endpoint = Endpoints.BLOCK_INFORMATION + block_hash;
        Response get_blockInformation_response = APIServices.get(get_blockInformation_endpoint);

        ObjectMapper objectMapper = new ObjectMapper();
        BlockInformation blockInformation = objectMapper.readValue(get_blockInformation_response.asString(), BlockInformation.class);

        int actualTransactionsCount = blockInformation.getTxCount();
        System.out.println("Actual Transactions count: "+actualTransactionsCount);
        Assert.assertEquals(actualTransactionsCount, expectedTransactionsCount, "Transactions count is not as expected");

    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void scenario2_validateTxid() throws JsonPathException, JsonProcessingException {

        boolean txid1_flag = false;
        boolean txid2_flag = false;

        get_txid:
        for(int index=0;index < (expectedTransactionsCount-25)+1; index += 25) {
            String get_txid_endpoint = Endpoints.BLOCK_INFORMATION + block_hash + "/txs/" + index;

            Response res = APIServices.get(get_txid_endpoint);
            ObjectMapper objectMapper = new ObjectMapper();

//            List<Transactions> transactions = res.jsonPath().getList("",Transactions.class);
            List<Transactions> transactions = objectMapper.readValue(res.asString(), new TypeReference<List<Transactions>>() {});

            for (Transactions transactions1 : transactions) {
                String txid = transactions1.getTxid();

                if(txid.equals(txid_1)){
                    txid1_flag = true;
                    System.out.println("Tx id 1 is found");
                }
                else if(txid.equals(txid_2)){
                    txid2_flag = true;
                    System.out.println("Tx id 2 is found");
                }

                if(txid1_flag && txid2_flag)
                    break get_txid;
            }
        }

        if(txid1_flag && txid2_flag){
            Assert.assertTrue(txid1_flag,txid_1+" not found");
            Assert.assertTrue(txid2_flag,txid_2+" not found");
        }
        else{
            Assert.fail("Expected txids are not found");
        }
    }
}
