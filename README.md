# WyFy

Wi-Fi change state adapter.

[<img
     src="https://fdroid.gitlab.io/artwork/badge/get-it-on.png"
     alt="Get it on F-Droid"
     height="80">](https://f-droid.org/packages/me.lucky.wyfy/)

<img 
     src="https://raw.githubusercontent.com/x13a/WyFy/main/fastlane/metadata/android/en-US/images/phoneScreenshots/1.png" 
     width="30%" 
     height="30%">

Tiny app to change Wi-Fi state via broadcast. 
Useful to bypass Google limitation on apps targeting `API 29` and higher.

You have to sent a broadcast message to `me.lucky.wyfy/.ControlReceiver` with one of the actions 
below.

Actions:
* me.lucky.wyfy.action.SET_ON
* me.lucky.wyfy.action.SET_OFF
* me.lucky.wyfy.action.TOGGLE

## Permissions

* ACCESS_WIFI_STATE - check  Wi-Fi state
* CHANGE_WIFI_STATE - change Wi-Fi state

## Example

```sh
$ adb shell am broadcast \
    -a me.lucky.wyfy.action.SET_ON \
    -n me.lucky.wyfy/.ControlReceiver
```

## License
[![GNU GPLv3 Image](https://www.gnu.org/graphics/gplv3-127x51.png)](https://www.gnu.org/licenses/gpl-3.0.en.html)

This application is Free Software: You can use, study share and improve it at your will.
Specifically you can redistribute and/or modify it under the terms of the
[GNU General Public License v3](https://www.gnu.org/licenses/gpl.html) as published by the Free
Software Foundation.
