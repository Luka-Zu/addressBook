# addressBook
restfull api for crud operations


Post:
http://localhost:8080/pe/auth/authenticate
RequestBody:{String:password, String:password}
it checks for user login and returnes key

Post:
http://localhost:8080/pe/auth/register
RequestBody:{String:password, String:password}
it registers a user

Get:
http://localhost:8080/contacts/private/{id}
RequestBody:{Long:id, String:username, String:imageUrl}
it updates a contact in database, needs token

Post:
http://localhost:8080/contacts/private/{id}
RequestBody:{Long:id, String:username, String:imageUrl}
adds contact in database, needs token

Delete:
http://localhost:8080/contacts/private/{id}
deletes contact by id, needs token 

RequestMapping:
http://localhost:8080/contacts/public/csv
downloads csv format of database cosnsisting of contact objects

Get:
http://localhost:8080/contacts/public/
gets all the contacts from database


Get:
http://localhost:8080/contacts/public/byName/{name}
gets one contact with appropriate name

Get
http://localhost:8080/contacts/public/{id}
gets one contact with appropriate id

