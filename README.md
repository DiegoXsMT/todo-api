# todo-api

Este proyecto es una aplicación Spring Boot para gestión de tareas con APIs REST.

---

## Requisitos previos

- Java 17 instalado
- Maven instalado
- MySQL instalado y corriendo
- Cliente HTTP para probar APIs (Postman, curl, Insomnia, etc.)

---

## Configuración inicial

### 1. Clonar el repositorio

```bash
git clone https://github.com/DiegoXsMT/todo-api.git
cd todo-api
```
### 2. Configurar la conexión a la base de datos
- Editaras el archivo src/main/resources/application.properties
  spring.datasource.url=jdbc:mysql://localhost:3308/tareas  (En mi caso utilice el puerto 3308)
  spring.datasource.username=tu_usuario
  spring.datasource.password=tu_contraseña
  spring.jpa.hibernate.ddl-auto=update

### 3. Ejecutar el proyecto
- Nos dirijimos a la terminal dentro de la carpeta del proyecto y ejecutamos esto para instalar todas las dependencias necesarias
- En mi caso lo realice en InteliJ (una vez ejecutado, lo iniciamos como normalmente iniciamos un proyecto (Mayus+F10))
![img_1.png](img_1.png)
```bash
mvn clean install

```


### 4. Pruebas de las Apis.
- Utilizaremos Postman para probar las peticiones
  - Se adjunta el archivo de colección Postman por correo.
- Crear tarea (Tipo POST):
  - URL: `http://localhost:8080/api/tareas`
  - Ejemplo de body JSON:
    ```json
    {
      "descripcion": "Estudiar",
      "fecha": "2025-10-10",
      "estatus":"completado" 
    }
    ```
    - Listar Tareas (Tipo GET):
  - URL: `http://localhost:8080/api/tareas`
  - Esto te listara todas las tareas existentes.


- Actualizar tarea (Tipo PUT):
  - URL: `http://localhost:8080/api/tareas/6`
  - Ejemplo de body JSON (En el ultimpo apartado de la url, es el id de la tarea a actualizar):
    ```json
    {
      "descripcion": "Estudiar",
      "fecha": "2025-10-10",
      "estatus":"en proceso" 
    }
    ```

    - Actualizar unicamente estatus (Tipo PATCH):
  - URL: `http://localhost:8080/api/tareas/2/estatus`
  - Ejemplo de body JSON (Aqui se manda como parametro el id de la tarea al cual le queremos actualizar el estatus):
    ```json
      "completado"
    ```
    - Podras utilizar los tres estados, pendiente, progreso y completado.

      - Eliminar tarea por ID (Tipo DELETE):
  - URL: `http://localhost:8080/api/tareas/6`
  - Ejemplo de body JSON (Aqui se manda como parametro el id de la tarea a eliminar):

- Buscar Tarea por ID (Tipo GET):
- URL: `http://localhost:8080/api/tareas/6`
- Ejemplo de body JSON (Aqui se manda como parametro el id de la tarea a buscar):
  ```json
    "id": 2,
    "descripcion": "Mantenimiento equipo",
    "fecha": "2025-06-15",
    "estatus": "pendiente"
  ```
 
    
    









