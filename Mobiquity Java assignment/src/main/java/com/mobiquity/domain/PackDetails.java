package com.mobiquity.domain;


import lombok.Builder;
import lombok.Getter;

/**
 * <h1>Pack Details</h1>
 * <p>This PackDetails is an immutable POJO class which acts as a model</p>
 *
 * @author  Vikash Chandrasekar
 * @version 1.0
 * @since   2020-03-25
 */


@Builder
@Getter
public class PackDetails implements Comparable<PackDetails> {

    private final int id;
    private final double weight;
    private final double price;

    /**
     * This method will compare and sort the items in the packDetails object
     *
     * @param o
     * @return int - This returns sorted int value
     */
    public int compareTo(PackDetails o) {
        return id - o.id;
    }
}
