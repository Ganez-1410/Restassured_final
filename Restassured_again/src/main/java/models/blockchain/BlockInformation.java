package models.blockchain;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlockInformation {
//    private String id;
//    private long height;
//    private long version;
//    private long timestamp;

    @JsonProperty("tx_count")
    private int txCount;

    @JsonIgnore
    private int size;
//    private long weight;
//    private String merkle_root;
//    private String previousblockhash;
//    private long mediantime;
//    private long nonce;
//    private long bits;
//    private double difficulty;
}
