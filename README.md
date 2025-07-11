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
├── src/main/java/com/camilosoto/prueba_tecnica
│   ├── persistence/           # Acceso a datos
│   ├── persistence/models     # modelos DynamoDB
│   ├── domain/services/       # Lógica de negocio
│   ├── web/config/            # configurations
│   ├── web/controllers/       # Controladores REST
│   └── seeder/                # Seeder de datos iniciales
├── resources/
│   └── application.properties
├── PruebaTecnicaApplication.java
├── Dockerfile
├── backend-infra.yaml         # Infraestructura (ECS, VPC, DynamoDB)
├── ecr-repo.yaml              # Repositorio ECR público
├── .github/workflows/deploy.yml
└── README.md
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

### Ejecutar localmente

```bash
docker build -t funds-api .
docker run -p 8080:8080 \
  -e AWS_REGION=us-east-1 \
  -e AWS_ACCESS_KEY_ID=<tu-access-key> \
  -e AWS_SECRET_ACCESS_KEY=<tu-secret-key> \
  funds-api
```
## ☁️ Despliegue automático

Este repositorio incluye despliegue automático mediante **GitHub Actions**:

### 1. Crear repositorio público en ECR

```bash
aws cloudformation deploy \
  --template-file ecr-repo.yaml \
  --stack-name funds-api-repo \
  --capabilities CAPABILITY_NAMED_IAM
```

### 2. Construir y subir la imagen

Automáticamente en `main`, o manualmente:

```bash
docker build -t funds-api .
docker tag funds-api:latest public.ecr.aws/<id>/casv/funds-api:latest
docker push public.ecr.aws/<id>/casv/funds-api:latest
```

### 3. Desplegar en ECS

```bash
aws cloudformation deploy \
  --template-file backend-infra.yaml \
  --stack-name funds-api-infra \
  --capabilities CAPABILITY_NAMED_IAM \
  --parameter-overrides ImageUri=public.ecr.aws/<id>/casv/funds-api:latest
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

---

## 🌐 Acceso al backend

Una vez desplegado:

```bash
http://<IP_PUBLICA>:8080/
```

Puedes obtener la IP desde la consola ECS o con:

```bash
aws ecs list-tasks --cluster funds-api-cluster
```

---

## 🧑 Autor

Camio Alejandro Soto Vega  
Prueba técnica backend Java + AWS
