## Overview
This adapter will send EVENT notification to hipchat room.
[Jennifersoft github](https://github.com/jennifersoft)

## Getting started

Execute the following in the Jennifer management screen.

 1. Extension & Notice > Adapter and Plugin.
 2. Select the Adapter tab.
 3. Click the Add button.
 4. Select the 'EVENT' type.
 5. Enter the 'hipchat' ID.
 6. Enter the path to the 'dist/adapter-hipchat-1.1.0.jar' file or upload it yourself.
 7. Input class name: com.aries.hipchat.HipchatAdapter and Click Save.
 ![screen shot 2018-11-29 at 15 10 20](https://user-images.githubusercontent.com/2956728/49202737-f00ac500-f3e8-11e8-9fbc-39c7c16b658e.png)
 8. set options
 ![screen shot 2018-11-29 at 15 13 10](https://user-images.githubusercontent.com/2956728/49202826-51329880-f3e9-11e8-9a65-572da25bc4ef.png)

 8. Restart jennifer view instance.
 
## How to use options

Plugin options are shown in the table below.

| Key           | Value | Remark |
| ------------- |:-------------:|:-------------:|
| hipchat_apiid |  2821321 | Hipchat Room's API ID |
| hipchat_token |  EFSFDSFE12312fdsfds | Hipchat Room's Token |
| hipchat_jennifer_url | http://localhost:9200/ | your Jennifer server URL |
| hipchat_noti_level| NORMAL or WARNING or FATAL|Notification level |
| hipchat_proxy_on | off | on or off |
| hipchat_proxy_host | my.proxy.com | proxy server host |
| hipchat_proxy_port | 3120 | proxy port number |

## Supported version
 
Different versions of the server support different plug-in versions.
 
| Plugin version | Jennifer server version | Java version |
| ------------- |-------------|-------------|
| 1.1.0       | Greater than or equal to version 5.4.0.0 | 1.8