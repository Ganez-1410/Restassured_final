package models.blockchain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Transactions {
    private String txid;
    private int version;
    private long locktime;
    private List<Vin> vin;
    private List<Vout> vout;
    private int size;
    private int weight;
    private int fee;
    private Status status;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Vin{
        private String txid;
        private long vout;
        private Prevout prevout;
        private String scriptsig;
        private String scriptsig_asm;
        private List<String> witness;

        @JsonProperty(value = "is_coinbase")
        private boolean isCoinbase;
        private long sequence;
        private String inner_redeemscript_asm;
        private String inner_witnessscript_asm;

        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        @ToString
        public static class Prevout{
            private String scriptpubkey;
            private String scriptpubkey_asm;
            private String scriptpubkey_type;
            private String scriptpubkey_address;
            private long value;
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Vout{
        private String scriptpubkey;
        private String scriptpubkey_asm;
        private String scriptpubkey_type;
        private String scriptpubkey_address;
        private long value;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Status{
        private boolean confirmed;
        private int block_height;
        private String block_hash;
        private long block_time;
    }

}
