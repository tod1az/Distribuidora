# DistribuidoraApp
DistribuidoraApp es una aplicación móvil desarrollada en Kotlin con Firebase Authentication y Firebase Realtime Database. Permite a los usuarios autenticarse (registro e inicio de sesión) y guardar su ubicación GPS en tiempo real. La aplicación está diseñada para distribuir productos y servicios basados en la ubicación del cliente.

Características
Autenticación de usuarios: Los usuarios pueden registrarse e iniciar sesión utilizando Firebase Authentication.
Almacenamiento de ubicación: La aplicación captura la ubicación GPS del usuario y la guarda en Firebase Realtime Database bajo el nodo locations, asociada al ID del usuario.
Interfaz moderna: Implementada con Jetpack Compose para una experiencia fluida.

Tecnologías Utilizadas
Kotlin: Lenguaje de programación principal para la aplicación Android.
Firebase Authentication: Para la autenticación de usuarios mediante correo electrónico y contraseña.
Firebase Realtime Database: Para almacenar la ubicación en tiempo real de los usuarios.
Jetpack Compose: Para la interfaz de usuario declarativa y moderna.
FusedLocationProviderClient: Para obtener la ubicación GPS del dispositivo.

## Instalación y Uso

* Clona este repositorio en tu máquina local:
bash
  ``` 
  git clone https://github.com/tod1az/Distribuidora.git
  ```
* Abre el proyecto en Android Studio.

* Configurar Firebase
  Crea un proyecto en Firebase y habilita Firebase             Authentication (correo y contraseña).

* Descarga el archivo google-services.json desde la consola de Firebase y colócalo en la carpeta /app de tu proyecto.

* Habilita Firebase Realtime Database y configura las reglas de seguridad para permitir la lectura y escritura durante el desarrollo:

  json
  ```
    {
    "rules": {
        ".read": true,
        ".write": true
      }
    }
  ```

## Ejecución
Abre el proyecto en Android Studio.
Sincroniza el proyecto con Gradle.
Conecta un dispositivo o usa un emulador para ejecutar la aplicación.
Regístrate o inicia sesión y permite los permisos de ubicación para que la aplicación capture y guarde tu ubicación GPS en Firebase.

## Estructura del Proyecto
MainActivity: Contiene la lógica para el registro y la autenticación de usuarios.
MenuActivity: Almacena la ubicación GPS del usuario y la envía a Firebase Realtime Database.
Firebase Realtime Database: Los datos se almacenan bajo el nodo locations, donde cada UID de usuario contiene su latitud y longitud.

## Requisitos
Android Studio 4.1 o superior.
Kotlin 1.4 o superior.
Conexión a Internet para interactuar con Firebase.

## Historias de usuarios

* Historia de Usuario - Registrarse
  Como usuario nuevo,
  necesito crear una cuenta en la aplicación mediante mi correo electrónico y una contraseña,
  para poder acceder a las funcionalidades de la aplicación y registrar mi ubicación para futuras entregas.

  **Criterios de aceptación**:
  El usuario debe poder registrarse proporcionando un correo electrónico válido y una contraseña.
  La contraseña debe tener un mínimo de 6 caracteres.
  Al registrarse exitosamente, el usuario debe ser redirigido al menú principal.
  La información del usuario debe ser almacenada de forma segura en Firebase Authentication.

* Historia de Usuario - Iniciar sesión
  Como usuario registrado,
  quiero iniciar sesión en la aplicación con mi correo electrónico y contraseña,
  para acceder a mi cuenta y usar las funcionalidades.

  **Criterios de aceptación**:
  El usuario debe iniciar sesión con las credenciales con las que se registró.
  Al iniciar sesión exitosamente, el usuario es redirigido al menú principal.
  Si las credenciales son incorrectas, debe mostrarse un mensaje de error.
  La autenticación debe gestionarse con Firebase Authentication. 


## Autores

- [@tod1az](https://www.github.com/tod1az)
- [@JHORNIG13](https://www.github.com/JHORNIG13)
