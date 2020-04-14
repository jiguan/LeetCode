package com.interview.lyft;

public class DifferentRacks {

}

class Solution {
    private static String[] hosts = {
        "asa-001", "asa-002", "asa-003", "asa-004", "asa-005", "asa-006",
        "asa-011", "asa-012", "asa-013", "asa-014", "asa-015", "asa-016",
        "asa-021", "asa-022", "asa-023", "asa-024", "asa-025", "asa-026",
        "asr-011", "asu-311", "asu-134", "asu-281", "asv-101", "asv-351",
        "asw-051", "asw-054", "asx-314", "asx-351", "asy-014", "asy-134",
        "asz-101", "asz-281", "asz-383", "ata-134", "ata-224", "ata-281",
        "ata-311", "atb-134", "atb-164", "atb-314", "atb-381", "atc-054",
        "atc-223", "atc-281", "atc-284", "atc-311", "add-223"
    };

    // @formatter:off
    // Build a list of replica sets from a list of hosts and the number of
    // hosts per replica set provided as input.
    //
    // The input hostnames are in the form of abc-123 where abc is the rack
    // and 123 is the position within the rack:
    //
    //    hostname
    //  ©°©¤©¤©¤©Ø©¤©¤©¤©´
    //  ¨‹       ¨‹
    //  asa - 022
    //  ¡ø ¡ø   ¡ø ¡ø
    //  ©¸©Ð©¼   ©¦ ©¦
    //   ©¦    ©¸©¤©Ø©¤©´
    // rack   position
    //
    // There are a couple of rules about how replica sets should be built:
    //   * Each replica set can only have one host from a given rack
    //     (only one `asa-###` host, for example)
    //   * Each host can only be in one replica set
    //
    // Example returned valid replica sets (from an optimal algorithm):
    // rsets = [
    //     ['add-223', 'asa-022', 'asz-101'],
    //     ['asa-001', 'atb-381', 'asz-281'],
    //     ...
    // ]
    // leftover = ['asa-024', 'asa-025', 'asa-026', 'asa-023', 'atc-311']
    //
    // Note that because we don't have evenly divisible numbers of machines from all our racks,
    // there are going to be some left over, that's okay as long as we know what they are.
    // @formatter:on

     public static void main(String[] args) {
    }
  }
