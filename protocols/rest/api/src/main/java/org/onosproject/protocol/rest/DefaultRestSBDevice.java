/*
 * Copyright 2016 Open Networking Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onosproject.protocol.rest;

import com.google.common.base.Preconditions;
import org.onlab.packet.IpAddress;
import org.onosproject.net.DeviceId;

import java.util.Objects;

/**
 * Default implementation for Rest devices.
 */
public class DefaultRestSBDevice implements RestSBDevice {
    private static final String REST = "rest";
    private static final String COLON = ":";
    private final IpAddress ip;
    private final int port;
    private final String name;
    private final String password;
    private boolean isActive;
    private String protocol;

    public DefaultRestSBDevice(IpAddress ip, int port, String name, String password,
                               String protocol, boolean isActive) {
        Preconditions.checkNotNull(ip, "IP address cannot be null");
        Preconditions.checkArgument(port > 0, "Port address cannot be negative");
        Preconditions.checkNotNull(protocol, "protocol address cannot be null");
        this.ip = ip;
        this.port = port;
        this.name = name;
        this.password = password;
        this.isActive = isActive;
        this.protocol = protocol;
    }

    @Override
    public IpAddress ip() {
        return ip;
    }

    @Override
    public int port() {
        return port;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public DeviceId deviceId() {
        return DeviceId.deviceId(REST + COLON + ip + COLON + port);
    }

    @Override
    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public String protocol() {
        return protocol;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RestSBDevice)) {
            return false;
        }
        RestSBDevice device = (RestSBDevice) obj;
        return this.name.equals(device.name()) && this.ip.equals(device.ip()) &&
                this.port == device.port();

    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, port);
    }

}
