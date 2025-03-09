FROM node:22.14 AS build-step
RUN mkdir -p /usr/local/app
WORKDIR /usr/local/app
COPY ./ /usr/local/app
RUN npm install
RUN npm run build --prod

FROM nginx:1.25.2
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build-step /usr/local/app/dist/frontend/browser /usr/share/nginx/html
RUN chmod -R 755 /usr/share/nginx/html
EXPOSE 80

LABEL authors="AntonioRodenasJurado"

# docker build -t frontend:1.0 .
# docker image list
# docker tag frontend:1.0 anrodenasj/frontend:1.0
# docker login
# hub.docker.com
# docker push anrodenasj/frontend:1.0
