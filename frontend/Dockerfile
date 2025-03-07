FROM node:18.19.0 AS build-step
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:1.23.1
COPY --from=build-step /app/dist/frontend/browser/* /usr/share/nginx/html/
COPY nginx.conf /etc/nginx/conf.d/default.conf
RUN chmod -R 755 /usr/share/nginx/html && \
    chown -R nginx:nginx /usr/share/nginx/html
EXPOSE 80

LABEL authors="AntonioRodenasJurado"

# docker build -t api-spring-angular-frontend .
# docker images list
