# Usa una imagen base de Node.js
FROM node:18 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos de package.json y package-lock.json
COPY package*.json ./

# Instala las dependencias
RUN npm install

# Copia el resto de los archivos de la aplicación
COPY . .

# Construye la aplicación de React
RUN npm run build

# Usa una imagen de Nginx para servir la aplicación construida
FROM nginx:alpine

# Copia los archivos de construcción a la carpeta de Nginx
COPY --from=build /app/dist /usr/share/nginx/html

# Expone el puerto en el que Nginx escucha
EXPOSE 80

# Comando para ejecutar Nginx en primer plano
CMD ["nginx", "-g", "daemon off;"]
