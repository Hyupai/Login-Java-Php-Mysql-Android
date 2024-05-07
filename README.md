# Aviso - Warning!
Br: Eu estarei usando o meu idioma nativo para descrever esse projeto, então caso você não esteja entendendo nada que estou digitando, utilize o tradutor do seu navegador. Obrigado!<br></br>
En: I'll be using my native language to describe this project, so if you don't understand anything I'm typing, use your browser's translator.  Thank you!

Obrigado ao @LGL, pelo layout e templates!
Obrigado a todos pelo apoio!

Contact Telegram @samuel_fcr

**Esse login é apenas para um dispositivo. Muito utilizado em ModMenu VIPs, injete seu mod separadamente se for usar. NAO reponderei duvidas que podem ser resolvidas no Google.**

# Programas necessários
- Android Studio/AIDE (eu recomendo o AndroidStudio)
- Apk Easy Tool
- Um servidor web com suporte para PHP
- Esse projeto

# Server
Eu deixei ele em Zip, assim você pode upar em sua Host. Modifique como desejar a parte em Html.
Crie seu banco de dados e faça sua conexão.

Caso nao esteja entendendo essa parte eu ensinei um pouco nesse video:https://youtu.be/jAgDdB7gw7A

# Editando seu projeto
Primeiro de tudo modifique sua URLSERVER na **Auth.java**, verifique sempre o http(s).<br><br>
![](https://i.imgur.com/5q2IDZX.png)<br></br>
Modifique também o layout se quiser na **MainActivity,java**, lembre-se que o título está em base 64 para evitar leechs.<br></br>
![](https://i.imgur.com/yb78gLU.png)<br></br>
Se você tiver usando o AIDE, verifique se o NDK está compilando corretamente a lib. (Eu tive problemas com isso)<br></br>
Agora,descubra a MainActivity do seu jogo.

Utilize o Apk Tool para vê-la:

![](https://i.imgur.com/JQdPjyZ.png)

Nesse caso está como `com.unity3d.player.UnityPlayerActivity`


No android studio ou AIDE modifique a `public String sGameActivity` se necessário!

![](https://i.imgur.com/KBBek2N.png)

Construa seu apk:

**Build** -> **Build Bundle(s)/APK(s)** -> **Build APK(s)**

Se tudo correr bem você será notificado.

![](https://i.imgur.com/WpSKV1L.png)

Clique em **locate** para mostrar o local do **build.apk**. 
![](https://i.imgur.com/wBTPSLi.png) 

# Implementando seu projeto no jogo

Agora decompile **app-debug.apk**.

Copie seu smali app-debug.apk descompilado para a pasta smali do jogo. O nosso exemplo é com.example.loginform2, copiaríamos a pasta `com` de` (app-debug \ smali \ com) `para o diretório descompilado do jogo` (nome do jogo) \ smali`. Se o jogo tiver multidexes, adicione seu smali às últimas classes dex se possível para evitar erros de compilação.

Na manifest do jogo `androidmanifest.xml`, fique claro que as seguintes permissoes exista: <br>`<uses-permission android:name="android.permission.INTERNET"/>`<br>`<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />`<br>
`<uses-permission android:name="android.permission.FOREGROUND_SERVICE" />`<br>
Caso não exista adicione-as.


![](https://i.imgur.com/k0sLVUF.png)

Agora localize a Activity Principal do jogo que você obteve anteriormente dentro da Android Manifest e depois remova:
`<action android:name="android.intent.action.MAIN"/>` desse jeito.

![](https://i.imgur.com/z1RxPjc.png)

Antes do fim da tag de Aplication, Adicione sua activity `</application>`. `com.example.loginform2.MainActivity` é sua activity principal.

```xml
<activity android:configChanges="keyboardHidden|orientation|screenSize" android:name="com.example.loginform2.MainActivity" android:screenOrientation="portrait">
     <intent-filter>
         <action android:name="android.intent.action.MAIN"/>
         <category android:name="android.intent.category.LAUNCHER"/>
     </intent-filter>
</activity>
```

![](https://i.imgur.com/X4b8jBV.png)

Agora compile APK do jogo.

Isso irá iniciar a tela de login e depois o jogo.

![](https://i.imgur.com/h0ZAn00.gif)

# Proteção na LIB

Veja `Main.cpp` por exemplo, como verificar se o usuário está logado

Existem os códigos chamados `Check ()` e `loadLibrary`. Eles estão comentados

Você pode precisar proteger seus arquivos dex e lib para isso.

**Não recomendo fazer isso pelo AIDE**



