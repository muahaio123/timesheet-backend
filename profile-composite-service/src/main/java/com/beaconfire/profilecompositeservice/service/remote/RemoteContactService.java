package com.beaconfire.profilecompositeservice.service.remote;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Richard Zhang
 */
@FeignClient("contact-service")
public interface RemoteContactService {
}
