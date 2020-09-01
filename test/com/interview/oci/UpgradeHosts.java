package com.interview.oci;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Input: list of hosts: [1, 2, 3, 4, 5] list of services, each running on some hosts : {a: [1, 2,
 * 4], b: [2, 3], c: [4, 5], d: [5, 6]}
 * 
 * We want to upgrade all hosts, find an order to do so. We need to make sure at any moment all
 * service have at most one host offline.
 * 
 * result: [[1, 3, 5], [2, 6], [4]]¡£
 * 
 */
public class UpgradeHosts {
    public static Set<Set<String>> findUpdateSets(Set<String> hosts,
            Map<String, Set<String>> services) {
        Set<Set<String>> result = new HashSet<>();
        while (!hosts.isEmpty()) {
            Set<String> updateSet = findUpdateSet(hosts, services);
            updateSet.forEach(h -> hosts.remove(h));
            result.add(updateSet);
        }
        return result;
    }

    private static Set<String> findUpdateSet(Set<String> hosts, Map<String, Set<String>> services) {
        Set<String> updateSet = new HashSet<>();
        Set<String> impactedServices = new HashSet<>();

        for (String host : hosts) {
            boolean canTakeDown = true;
            Set<String> impactedServicesByHost = new HashSet<>();
            for (String service : services.keySet()) {
                if (services.get(service).contains(host)) {
                    if (impactedServices.contains(service)) {
                        canTakeDown = false;
                        break;
                    } else {
                        impactedServicesByHost.add(service);
                        impactedServices.add(service); // service not impacted before, can take one
                                                       // host out
                    }
                }
            }
            if (canTakeDown) {
                updateSet.add(host);
            } else {
                impactedServicesByHost.forEach(s -> impactedServices.remove(s));
            }
        }
        return updateSet;
    }
}
