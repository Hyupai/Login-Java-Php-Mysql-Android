# Aviso - Warning!
Br: Eu estarei usando o meu idioma nativo para descrever esse projeto, então caso você não esteja entendendo nada que estou digitando, utilize o tradutor do seu navegador. Obrigado!<br></br>
En: I'll be using my native language to describe this project, so if you don't understand anything I'm typing, use your browser's translator.  Thank you!

# Programas necessários
- Android Studio/AIDE (eu recomendo o AndroidStudio)
- Apk Easy Tool
- Um servidor web com suporte para PHP
- Esse projeto

Caso você não saiba fazer upload do server ou fazer um veja esse video:

# Editando seu projeto

Primeiro de tudo modifique sua URLSERVER, verifique sempre o http(s).<br><br>
Img<br></br>
Modifique também o layout se quiser, lembre-se que o título está em base 64 para evitar leechs.<br></br>
Img<br></br>
Se você tiver usando o AIDE, verifique se o NDK está compilando corretamente a lib. (Eu tive problemas com isso)<br></br>
Agora,descubra a MainActivity do seu jogo.

Utilize o Apk Tool para vê-la:
![](https://i.imgur.com/JQdPjyZ.png)

Nesse caso está como `com.unity3d.player.UnityPlayerActivity`

![](https://i.imgur.com/FfOtc1K.png)

No android studio ou AIDE modifique a `public String sGameActivity` se necessário!

![](https://i.imgur.com/gstiBDk.png)

Construa seu apk:

**Build** -> **Build Bundle(s)/APK(s)** -> **Build APK(s)**

Se tudo correr bem você será notificado.

![](https://i.imgur.com/WpSKV1L.png)

Clique em **locate** para mostrar o local do **build.apk**. 
![](https://i.imgur.com/wBTPSLi.png) 

# Implementando seu projeto no jogo





