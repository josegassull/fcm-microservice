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
  "token": "abc123def456ghi789",
  "userExternalId": 67890,
  "device": {
    "uuid": "550e8400-e29b-41d4-a716-446655440000",
    "type": "iOS"
  },
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
| `token` | String | The registered device token |
| `userExternalId` | Number | External user identifier |
| `device` | Object | Device information |
| `device.uuid` | String | Device UUID |
| `device.type` | String | Device type |
| `httpStatus` | Object | HTTP status information |
| `httpStatus.name` | String | HTTP status name |
| `httpStatus.code` | Number | HTTP status code |

**Status Codes:**
- `201 Created` - Token registered successfully
- `200 OK` - Token updated successfully
- `400 Bad Request` - Invalid request body or missing required fields

---

## Error Handling

TODO ErrorResponseDTO

## Contributing

TODO

## License

TODO
