/*******************************************************************************
 * Copyright (c) 2017 Ash (QuarkTheAwesome) & Maschell
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
package net.ash.HIDToVPADNetworkClient.hid.purejavahid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.ash.HIDToVPADNetworkClient.hid.HidDevice;
import net.ash.HIDToVPADNetworkClient.hid.HidManagerBackend;
import purejavahidapi.PureJavaHidApi;

public class PureJavaHidManagerBackend implements HidManagerBackend {
    @Override
    public List<HidDevice> enumerateDevices() {
        List<HidDevice> result = new ArrayList<HidDevice>();
        for (purejavahidapi.HidDeviceInfo info : PureJavaHidApi.enumerateDevices()) {
            result.add(new PureJavaHidDevice(info));
        }
        return result;
    }

    @Override
    public HidDevice getDeviceByPath(String path) throws IOException {
        List<purejavahidapi.HidDeviceInfo> devList = PureJavaHidApi.enumerateDevices();
        HidDevice result = null;
        for (purejavahidapi.HidDeviceInfo info : devList) {
            String real_path = info.getPath();
            if (real_path.equals(path)) {
                return new PureJavaHidDevice(info);
            }
        }

        return result;
    }
}
