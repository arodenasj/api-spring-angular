FROM node:18.19.0 AS build-step
RUN mkdir -p /app
WORKDIR /app
COPY ./ /app
RUN npm install
RUN npm run build --prod

FROM nginx:1.21.3
RUN rm -rf /usr/share/nginx/html/*
COPY --from=build-step /app/dist/frontend /usr/share/nginx/html

EXPOSE 80
LABEL authors="AntonioRodenasJurado"

# docker build -t cv-sprin-angular-frontend .
# docker images list
