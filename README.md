# FCM Microservice API Documentation

An initial REST API for Firebase Cloud Messaging token management and notifications to users.

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

---

## Error Handling

TODO ErrorResponseDTO

## Contributing

TODO

## License

TODO
