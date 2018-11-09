## Overview
This adapter will send EVENT notification to hipchat room.
[Jennifersoft github](https://github.com/jennifersoft)

## Adapter version
| Adpter version| JENNIFER Server Version| remark|
| ------------- |:-------------|:-------------|
| 1.0.0     | JENNIFER version > 5.2.3 | To check [v1.0.0]Getting started |
| 1.1.0     | JENNIFER version >= 5.4.0 | |

## Requirements
To use this adapter you will need below
- hipchat_apiid
- hipchat_token
- hipchat_jennifer_url (Your jennifer server URL)
- hipchat_noti_level (From what level do notification to user)
- hipchat_proxy_on (on or off)
- hipchat_proxy_host
- hipchat_proxy_port

## Adapter Settings
Once you have your token and apiid you can proceed with the adapter installation/configuration as follow:

The first step is to register the adapter:

1. In JENNIFER Client, open the management area and Navigate to Extension and Notice > Adapter and Plugin
2. Make sure the adapter tab is selected then click the [+Add] button
3. Select [Event] from the classifications dropdown.
4. Enter hipchat as the adapter ID.
5. Enter the path to the adapter JAR file.
6. Enter the adapter class name adapter.jennifer.hipchat.HipchatAdapter and save the settings.

### Options
| key| Description | example |
|------------- |:-------------|:-------------|
| hipchat_apiid| Hipchat Room's API ID | 2821321 |
| hipchat_token| Hipchat Room's Token | EFSFDSFE12312fdsfds |
| hipchat_jennifer_url| your Jennifer server URL | http://localhost:9200/ |
| hipchat_noti_level| Notification level | NORMAL or WARNING or FATAL |
| hipchat_proxy_on| if "on" usgin proxy | on or off |
| hipchat_proxy_host| proxy server host | localProxy |
| hipchat_proxy_port| proxy server port | 3210 |

The table shows the required options for this adapter


## [v1.0.0]Getting started
You must modify the configuration file of the view server. (conf/server_view.conf)
```
adapter_class_path = /usr/local/jennifer/jennifer5/server.view/ext/email.adapter/jennifer-view-adapter-hipchat.jar
adapter_config_path = /usr/local/jennifer/jennifer5/server.view/ext/hipchat.adapter/adapter.properties
adapter_event_class_name = adapter.jennifer.hipchat.HipchatAdapter
```

## Configuration file
The configuration file looks like the following
```
# Set Hipchat server URL
hipchat_api_server=https://api.hipchat.com/v2/room/

# Set Hipchat APIID
hipchat_apiid=9999999

# Set Hipchat Room token
hipchat_token=XXXXXXXX

# Set your jennifer server login URL
hipchat_jennifer_url=http://localhost:7900/login

# Set NORMAL or WARNING or FATAL
hipchat_noti_level=FATAL


# Set proxy setting
# Example : "on" or "off"
# Example : on -> user proxy, off -> direct access
hipchat_proxy_on=on
hipchat_proxy_host=proxy-host
hipchat_proxy_port=9999
```

* Make sure to replace the hipchat property with the correct value

## version history
2017-01-17 : v1.0.0 default functions. 

## For develop
Add class path
```
{{ VIEW_SERVER_HOME }}/lib
```
For avoid "@Override" error in HipchatAdapter
```
Project language level: 6 - @Override in interfaces
```
### Class Diagrams
![Class Diagrams](https://cloud.githubusercontent.com/assets/2956728/22003145/93fc46de-dc94-11e6-838e-d0fe39fa3271.png)