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

After toggling it ON, to change Wi-Fi state you have to send broadcast to 
`me.lucky.wyfy/.ControlReceiver` with the action below. Also you may require code authentication to 
limit access to controller by long clicking on the code block (red - disabled, yellow - enabled). 
While authentication enabled you have to send this code in broadcast extra with the key `code`.

Broadcasts can be sent with [Key Mapper](https://github.com/sds100/KeyMapper), apps for automation 
like [Automate](https://play.google.com/store/apps/details?id=com.llamalab.automate)/
[Tasker](https://play.google.com/store/apps/details?id=net.dinglisch.android.taskerm)/
[MacroDroid](https://play.google.com/store/apps/details?id=com.arlosoft.macrodroid) and so on.

Actions:
* me.lucky.wyfy.action.SET_ON
* me.lucky.wyfy.action.SET_OFF

## Permissions

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
