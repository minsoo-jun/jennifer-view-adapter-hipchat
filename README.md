## Overview
This adapter will send EVENT notification to hipchat room.
[Jennifersoft gitbub](https://github.com/jennifersoft)

## Getting started
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