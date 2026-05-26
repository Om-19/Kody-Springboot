Rule to Remember

Annotation	        Owns Relationship?
@ManyToOne	            YES
@OneToMany(mappedBy)	NO

Foreign key ALWAYS goes on:
@ManyToOne
owning side

@ManyToOne = child table stores foreign key
@OneToMany(mappedBy) = parent can access children

Annotation	    Used For
@NotNull	    objects
@NotBlank	    strings
@Size	        string length
@Min/@Max	    numbers
@Email	        email

Authentication   Who are you?
Example:
email + password login

Authorization  What are you allowed to do?
Example:
STUDENT can enroll
INSTRUCTOR can create course

SPRING SECURITY JWT FLOW
Login
  ↓
Generate JWT Token
  ↓
Frontend stores token
  ↓
Frontend sends token in Authorization header
  ↓
Backend validates token
  ↓
API access granted

1. created auth service for registration
2. Spring security config 
        password encoding 
        disable csrf
3. JWT UTIL
4. Service Login method
5. Controller

6. CustomerDetailsService
7. JWT Filter
8. Register Filter In SecurityConfig

=====Authorization========
customize SecurityFilterChain BEAN in SECURITY CONFIG

WHAT IS 403?
Status	Meaning
401	Not logged in
403	Logged in but not allowed

Registration
Password
   ↓
BCrypt Hash
   ↓
Stored in DB

Login
Raw Password
   ↓
BCrypt matches()
   ↓
Success
   ↓
Generate JWT token using jwt.secret
Protected APIs
JWT token
   ↓
Validate using jwt.secret
Best Mental Model
Feature	Uses
BCrypt	password security
JWT Secret	token security


Mapping Type	Meaning
Uni-directional	only one entity can navigate
Bi-directional	both entities can navigate
| Mapping    | FK Stored In   |
| ---------- | -------------- |
| OneToMany  | Many side      |
| ManyToOne  | Current entity |
| OneToOne   | Owning side    |
| ManyToMany | Join table     |
