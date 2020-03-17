package com.interview.karat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

/*
 * The people who buy ads on our network don't have enough data about how ads are working for their
 * business. They've asked us to find out which ads produce the most purchases on their websites.
 * 
 * Our client provided us with a list of user IDs of customers who bought something on a landing
 * page after clicking one of their ads:
 * 
 * # Each user completed 1 purchase. completed_purchase_user_ids = [ "3123122444","234111110",
 * "8321125440", "99911063"]
 * 
 * And our ops team provided us with some raw log data from our ad server showing every time a user
 * clicked on one of our ads:
 * 
 * ad_clicks = [ #"IP_Address,Time,Ad_Text",
 * "122.121.0.1,2016-11-03 11:41:19,Buy wool coats for your pets",
 * "96.3.199.11,2016-10-15 20:18:31,2017 Pet Mittens",
 * "122.121.0.250,2016-11-01 06:13:13,The Best Hollywood Coats",
 * "82.1.106.8,2016-11-12 23:05:14,Buy wool coats for your pets",
 * "92.130.6.144,2017-01-01 03:18:55,Buy wool coats for your pets",
 * "122.121.0.155,2017-01-01 03:18:55,Buy wool coats for your pets",
 * "92.130.6.145,2017-01-01 03:18:55,2017 Pet Mittens", ]
 * 
 * The client also sent over the IP addresses of all their users.
 * 
 * all_user_ips = [ #"User_ID,IP_Address", "2339985511,122.121.0.155", "234111110,122.121.0.1",
 * "3123122444,92.130.6.145", "39471289472,2001:0db8:ac10:fe01:0000:0000:0000:0000",
 * "8321125440,82.1.106.8", "99911063,92.130.6.144" ]
 * 
 * Write a function to parse this data, determine how many times each ad was clicked, then return
 * the ad text, that ad's number of clicks, and how many of those ad clicks were from users who made
 * a purchase.
 * 
 * Expected output:
 * 
 * 1 of 2 2017 Pet Mittens
 * 
 * 0 of 1 The Best Hollywood Coats
 * 
 * 3 of 4 Buy wool coats for your pets
 * 
 * purchases: number of purchase IDs clicks: number of ad clicks ips: number of IP addresses
 * 
 */

public class AdAttribution {

    // ad_clicks: "IP_Address,Time,Ad_Text"
    // completed_purchase_user_ids
    // all_user_ips: "User_ID,IP_Address"
    public List<String> getSummary(String[] ad_clicks, String[] completed_purchase_user_ids,
            String[] all_user_ips) {
        // IP - userId
        Map<String, String> users = new HashMap<>();
        for (String str : all_user_ips) {
            String[] arr = str.split(",");
            users.put(arr[1], arr[0]);
        }

        // ad_text - userId
        Map<String, Set<String>> clicks = new HashMap<>();
        for (String click : ad_clicks) {
            String[] arr = click.split(",");
            String ip = arr[0];
            String ad_text = arr[2];
            if (!clicks.containsKey(ad_text)) {
                clicks.put(ad_text, new HashSet<>());
            }
            String userId = users.get(ip);
            clicks.get(ad_text).add(userId);
        }

        // userId
        Set<String> purchases = new HashSet<>(Arrays.asList(completed_purchase_user_ids));

        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : clicks.entrySet()) {
            String ad_text = entry.getKey();
            Set<String> clickedUserIds = entry.getValue();
            int valid = 0;
            for (String userId : clickedUserIds) {
                if (purchases.contains(userId)) {
                    valid++;
                }
            }
            res.add(valid + " of " + clickedUserIds.size() + " " + ad_text);
        }
        return res;
    }

    @Test
    public void test0() {
        String[] ad_clicks = {"122.121.0.1,2016-11-03 11:41:19,Buy wool coats for your pets",
                "96.3.199.11,2016-10-15 20:18:31,2017 Pet Mittens",
                "122.121.0.250,2016-11-01 06:13:13,The Best Hollywood Coats",
                "82.1.106.8,2016-11-12 23:05:14,Buy wool coats for your pets",
                "92.130.6.144,2017-01-01 03:18:55,Buy wool coats for your pets",
                "122.121.0.155,2017-01-01 03:18:55,Buy wool coats for your pets",
                "92.130.6.145,2017-01-01 03:18:55,2017 Pet Mittens"};

        String[] all_user_ips = {"2339985511,122.121.0.155", "234111110,122.121.0.1",
                "3123122444,92.130.6.145", "39471289472,2001:0db8:ac10:fe01:0000:0000:0000:0000",
                "8321125440,82.1.106.8", "99911063,92.130.6.144"};

        String[] completed_purchase_user_ids =
                {"3123122444", "234111110", "8321125440", "99911063"};

        List<String> res = getSummary(ad_clicks, completed_purchase_user_ids, all_user_ips);
        /*
         * 1 of 2 2017 Pet Mittens
         * 
         * 0 of 1 The Best Hollywood Coats
         * 
         * 3 of 4 Buy wool coats for your pets
         */
        System.out.println(res);
    }
}
