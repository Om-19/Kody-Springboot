Exception Handling

get, delete
update 

You MUST implement:

✅ 1. Handle each custom exception separately
Different HTTP statuses:
NOT_FOUND → 404
BAD_REQUEST → 400
FORBIDDEN → 403


✅ 2. Create a Standard Error Response DTO
Include:
message
status
timestamp
path


✅ 3. Handle Validation Errors
Use:
@Valid
Handle:
MethodArgumentNotValidException
Return:
{
  "field": "error message"
}


✅ 4. Handle Generic Exception
@ExceptionHandler(Exception.class)
Return:
"Something went wrong"

========================================================================
com.scms

 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto

 ├── exception
 │     ├── ComplaintNotFoundException
 │     ├── UnauthorizedActionException
 │     ├── InvalidComplaintStateException
 │     ├── ComplaintAlreadyResolvedException

 ├── handler
 │     ├── GlobalExceptionHandler

 └── enums
       ├── ComplaintStatus
       ├── UserRole