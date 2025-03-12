FROM node:22.14 AS build-step
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build --prod

FROM nginx:1.23.1
COPY --from=build-step /app/dist/frontend/* /usr/share/nginx/html/
COPY nginx.conf /etc/nginx/conf.d/default.conf

RUN chmod -R 755 /usr/share/nginx/html && \
    chown -R nginx:nginx /usr/share/nginx/html

EXPOSE 80

LABEL authors="AntonioRodenasJurado"

# docker build -t frontend:2.0 .
# docker image list
# docker tag frontend:2.0 antoniorodenas/frontend:2.0
# docker login
# hub.docker.com
# docker push antoniorodenas/frontend:2.0
