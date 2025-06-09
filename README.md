# FCM Microservice API Documentation

An initial REST API for Firebase Cloud Messaging token management and notifications to users.

## Quickstart

### Requirements with recommended versions
- Docker Engine 28.+
- Docker Compose 2.+

### Development Setup

1. **Clone and navigate to project**
```bash
git clone https://github.com/sstefanofm/fcm-microservice.git
cd fcm-microservice
```

2. **Start development environment**
```bash
docker compose -f docker-compose.dev.yml --env-file .env.dev up -d
```

3. **Test if the REST API is up and running**
```http
GET http://localhost:8080/api/hello-world
```

4. **Enter the Postgres database**
```bash
docker exec -it fcm_ms_postgres_dev psql -U postgres_dev -d fcm_ms_dev
```
Postgres credentials are managed in the `.env.dev` file

4. **Stop development environment**
```bash
docker-compose -f docker-compose.dev.yml down
```

### Development Features
- **Hot Reload**: Code changes in `./token-api/src` are automatically reflected
- **Database Persistence**: Data survives container restarts
- **Live Development**: No need to rebuild containers for code changes under `./token-api/src`

## Base URL

```
http://localhost:8080/api
```

## Endpoints

### Health Check

#### GET `/hello-world`

Test endpoint to verify if the API is running.

**Request:**
```http
GET http://localhost:8080/api/hello-world
```

**Response:**
```
Hello world!
```

**Status Codes:**
- `200 OK` - API is running successfully

---

### Token Management

#### POST `/token/register`

Register a device token for push notifications.

**Request:**
```http
POST http://localhost:8080/api/token/register
Content-Type: application/json

{
  "token": "<your-FCM-token-here>",
  "deviceUuid": "<device-unique-identifier>",
  "deviceType": "Android|iOS|other",
  "userExternalId": 12345
}
```

**Request Body Parameters:**

| Parameter | Type | Description | Required |
|-----------|------|-------------|----------|
| `token` | String | Device push notification token | Yes |
| `deviceUuid` | String | Unique device identifier | Yes |
| `deviceType` | String | Device platform (`Android`, `iOS`, or `other`) | Yes |
| `userExternalId` | Number | External user identifier | Yes |

**Example Request:**
```bash
curl -X POST http://localhost:8080/api/token/register \
  -H "Content-Type: application/json" \
  -d '{
    "token": "abc123def456ghi789",
    "deviceUuid": "550e8400-e29b-41d4-a716-446655440000",
    "deviceType": "iOS",
    "userExternalId": 67890
  }'
```

**Response:**
```json
{
  "message": "Created token",
  "description": "Successfully created token for user with external_id '1' and device with uuid '123e4567-e89b-12d3-aaaasd-4266a14174000'",
  "httpStatus": {
    "name": "CREATED",
    "code": 201
  }
}
```

**Response Fields:**

| Field | Type | Description |
|-------|------|-------------|
| `message` | String | Success message |
| `description` | String | Detailed description of the operation |
| `httpStatus` | Object | HTTP status information |
| `httpStatus.name` | String | HTTP status name |
| `httpStatus.code` | Number | HTTP status code |

**Status Codes:**
- `201 Created` - Token registered successfully
- `200 OK` - Token updated successfully
- `400 Bad Request` - Invalid request body or missing required fields

---

### Notifications

#### POST `/notify/user/{user_external_id}`

Send push notifications to all devices registered for a specific user.

**Request:**
```http
POST http://localhost:8080/api/notify/user/43
Content-Type: application/json

{
  "title": "Hello!!",
  "body": "This is a notification body",
  "data": {
    "key1": true,
    "key2": "Foo",
    "key3": 123
  }
}
```

**Path Parameters:**

| Parameter | Type | Description | Required |
|-----------|------|-------------|----------|
| `user_external_id` | Number | External user identifier to send notifications to | Yes |

**Request Body Parameters:**

| Parameter | Type | Description | Required |
|-----------|------|-------------|----------|
| `title` | String | Notification title | Yes |
| `body` | String | Notification message body | Yes |
| `data` | Object | Additional custom data to include with notification | No |

**Response:**
```json
{
  "timestamp": "2025-06-08T22:12:53.740323358",
  "message": "Fired notifications for '10' instances of user with external_id '43'",
  "notifiedDevicesCounts": {
    "failure": 4,
    "success": 6
  },
  "httpStatus": {
    "name": "PARTIAL_CONTENT",
    "code": 206
  }
}
```

**Response Fields:**

| Field | Type | Description |
|-------|------|-------------|
| `timestamp` | String | ISO timestamp of when the notification was sent |
| `message` | String | Summary message of the operation |
| `notifiedDevicesCounts` | Object | Notification delivery statistics |
| `notifiedDevicesCounts.failure` | Number | Number of devices that failed to receive notification |
| `notifiedDevicesCounts.success` | Number | Number of devices that successfully received notification |
| `httpStatus` | Object | HTTP status information |
| `httpStatus.name` | String | HTTP status name |
| `httpStatus.code` | Number | HTTP status code |

**Example Request:**
```bash
curl -X POST http://localhost:8080/api/notify/user/43 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "New Message",
    "body": "You have a new notification",
    "data": {
      "notify": 1122,
      "category": "message"
    }
  }'
```

**Status Codes:**
- `200 OK` - All notifications reached their destination
- `206 PARTIAL CONTENT` - Notifications **fired** successfully, at least one failed
- `400 Bad Request` - Invalid request body or missing required fields

## Error Handling

All endpoints return consistent error response formats with detailed field validation messages.

**Error Response:**
```json
{
  "httpStatus": {
    "name": "METHOD_NOT_ALLOWED",
    "code": 405
  },
  "timestamp": "2025-06-09T00:26:51.099613458",
  "error": "Method not allowed",
  "errors": {
    "GET": "This method is not supported for this endpoint"
  },
  "path": "/api/token/register"
}
```

**Error Response Structure:**

| Field | Type | Description |
|-------|------|-------------|
| `httpStatus` | Object | HTTP status information |
| `httpStatus.name` | String | HTTP status name (e.g., "BAD_REQUEST") |
| `httpStatus.code` | Number | HTTP status code (e.g., 400) |
| `timestamp` | String | ISO timestamp of when the error occurred |
| `error` | String | General error message |
| `errors` | Object | Field-specific validation errors (when applicable) |
| `path` | String | API endpoint path where the error occurred |

**Common Error Scenarios:**
- **400 Bad Request**: Missing required fields, invalid JSON format, or validation failures
- **404 Not Found**: Resource not found (e.g., request to /notify/dinosaur)
- **405 Method Not Allowed**: Method not supported for the current endpoint. The endpoint exists, but the method used is not available
- **500 Internal Server Error**: Unexpected server errors

## License

### MIT License

<p>Copyright (c) 2025 Stefano Federici Marsegani</p>

<p>Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:</p>

<p>The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.</p>

<p>THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.</p>
