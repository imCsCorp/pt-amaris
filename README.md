# 📝 Prueba Técnica Backend

## ✅ Descripción

Este proyecto es una API REST desarrollada en **Java Spring Boot**. La solución está completamente desplegada en **AWS** utilizando **ECS Fargate**, **DynamoDB**, **SES** y **CloudFormation**.

---

## 📦 Tecnologías utilizadas

- Java 17 + Spring Boot 3
- AWS DynamoDB (persistencia)
- AWS SES (notificaciones por email)
- AWS ECS Fargate (contenedorización y despliegue)
- AWS ECR (repositorio de imagen)
- AWS CloudFormation (infraestructura como código)
- Docker + Maven
- Lombok

---

## 📁 Estructura del proyecto

```
src/
└── main/
    └── java/com/camilosoto/prueba_tecnica/
        ├── domain/services/      # Lógica de negocio
        │   ├── EmailService.java
        │   ├── FundService.java
        │   ├── NotificationService.java
        │   └── ...
        │
        ├── persistence/          # Acceso a datos y modelos DynamoDB
        │   ├── models/
        │   │   ├── Fund.java
        │   │   ├── Transaction.java
        │   │   └── User.java
        │   ├── FundRepository.java
        │   ├── TransactionRepository.java
        │   └── UserRepository.java
        │
        ├── web/                  # Capa web (API REST y config)
        │   ├── config/
        │   │   ├── DynamoDbConfig.java
        │   │   ├── SesConfig.java
        │   │   └── SnsConfig.java
        │   ├── controllers/
        │   │   └── FundController.java
        │   └── dto/
        │       └── FundActionRequest.java
        │
        └── PruebaTecnicaApplication.java
```

---

## 🚀 Endpoints disponibles

| Método | Ruta                       | Descripción                                 |
|--------|----------------------------|---------------------------------------------|
| GET    | `/api/funds`              | Obtener todos los fondos                    |
| GET    | `/api/funds/transactions` | Obtener historial de transacciones          |
| POST   | `/api/funds/subscribe`    | Vincular usuario a un fondo                 |
| POST   | `/api/funds/cancel`       | Cancelar vinculación de un fondo            |

> Las solicitudes POST deben incluir: `userId`, `fundId`, y `notificationType` (`email` o `sms`)

---

## 🔐 Notificaciones

Se envían automáticamente tras cada vinculación o cancelación usando:
- 📧 **AWS SES** (email)
- 📲 **AWS SNS** (SMS) *(opcional)*

---

## 🐳 Ejecutar localmente con Docker

```bash
docker-compose up --build
```

---

## 📦 Subir imagen a AWS ECR

```bash
# Login en ECR
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin <tu_id>.dkr.ecr.us-east-1.amazonaws.com

# Crear repositorio (solo 1 vez)
aws ecr create-repository --repository-name fondos-api

# Build + tag + push
docker build -t fondos-api .
docker tag fondos-api:latest <tu_id>.dkr.ecr.us-east-1.amazonaws.com/fondos-api:latest
docker push <tu_id>.dkr.ecr.us-east-1.amazonaws.com/fondos-api:latest
```

---

## ☁️ Desplegar en AWS con CloudFormation

```bash
aws cloudformation deploy \
  --template-file cloudformation.yml \
  --stack-name fondos-api-stack \
  --capabilities CAPABILITY_IAM \
  --parameter-overrides ImageUri=<tu_id>.dkr.ecr.us-east-1.amazonaws.com/fondos-api:latest
```

---

## 📄 Credenciales AWS (modo local)

Debes definir las siguientes variables si no usas `aws configure`:

```bash
export AWS_ACCESS_KEY_ID=xxxx
export AWS_SECRET_ACCESS_KEY=yyyy
export AWS_REGION=us-east-1
```

---

## ✅ Consideraciones

- El código sigue principios de **clean code** y separación de responsabilidades
- Utiliza `@DynamoDbBean` y `DynamoDbEnhancedClient` para acceso a DynamoDB
- Los modelos usan **Lombok** para reducir boilerplate
- La infraestructura completa puede ser reimplementada con solo 1 archivo (`cloudformation.yml`)

---

## 🧑 Autor

Camio Alejandro Soto Vega  
Prueba técnica backend Java + AWS
