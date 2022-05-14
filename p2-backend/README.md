one to one:
each one has a field from the other table that not appear in the tables
there is join column in one of the one's which appear in that table
and the other mappes to that join column

one to many, many to one:
in one we have a list of many table which will not appear in the table
in many we have a field from the one table not appear in the table
in many we will have a join column which will appear in the table

many to many:
makes a join table consisting one foreign keyfrom each table




Users: 
one to many to Orders
many to many to Address

Orders:
many to one to User
many to one to Address
ont to many to OrderItem

OrderItem:
many to one to Orders
one to one to Product

Product:
one to one to OrderItem
many to one to Category

Category:
one to many to OrderItem


# Revagenda
So we have three layers
1 - Persistent : repositories
2 - service
3 - api(servlets) : controllers


Spring Annotations
Spring Config
@Configuration - marks a class as the bean configuration class which has methods that
return objects to be beanified.
@ComponentScan - tells IoC container to configure by scanning for annotated components
@Bean - marks a class as a bean (not for component scanning!)
@Component - marks a class as a component (a bean) for component scanning. There are
also the "stereotype" annotations below:
@Repository - marks as a JPA repository bean
@Service - marks as a service bean
@Controller - marks as a controller (servlet) bean
@Autowired - injects dependencies from IoC contrainer automagically
@Resource - alternative to @Autowired, this injects beans into fields or setters. Not constructors!
@Inject - alternative to @Autowired, this injects beans into fields or setters. Not constructors!
@Qualifier - In addition to byName and byType you can find beans by qualifier.
@Required - indicates the annotated bean field is required and a bean cannot be instantiated
if it is not supplied.
@Inject - Another way to inject dependencies from IoC container
@Named - Allows @Inject to find beans byName instead of byType


Spring MVC (Web)
@Controller - stereotype annotation that marks class as a controller (basically a servlet)
@Restcontroller - Implies @Controller and @ResponseBody
@PathVariable - marks a parameter in a controller method to be populated by the correpsonding
path variable
@RequestMapping - maps a controller or method to a URL pattern
@GetMapping - @RequestMapping that denotes the GET method
@PostMapping - @RequestMapping that denotes the POST method
@PutMapping - @RequestMapping that denotes the PUT method
@DeleteMapping - @RequestMapping that denotes the DELETE method
@RequestBody - Marks a parameter as a request payload that should be de-serialized from JSON/XML
@ResponseBody - This annotation marks a return type to be serialized into JSON/XML payload
@ResponseStatus - Use this annotation to set the response status for a successful method
@RequestParam - Marks a parameter in a controller method to be populated with the value
associated with a key in the request payload
@RequestHeader - marks a parameter in a controller method to be populated with the value
associated with a key in the request headers

Transmitting Data
There are a number of ways to transmit data as part of these requests and responses:

1-Path variables(@PathVariable) and a query parameter
www.bank.com/api/user/{3}/account/{5}/transaction/{2022-05-08}?type=deposit

2-lots of query parameters
www.bank.com/api/transactions?user=3&account=5&transaction=2022-05-08&type=deposit

3-headers

www.bank.com/api/transactions
headers:
user=3
account=5
transaction=2022...
type=deposit

4-JSON body

www.bank.com/api/transactions
body:
{
"user" : 3
"account" : 5
"transaction" : "2022-05-08"
"type" : "deposit"
}
What we want to do is probably a mix of all 4:

Path parameters for resource pathing through a restful hierarchy of resources
query params for anything else that has to do with the content of the request
headers for anything that has to do with the request itself, not the contents (request metadata)
JSON body for anything that is a resource representation


Create:Post
Read:Get
Update:Put
Delete:Delete


Hibernate annotations used in the above class are listed below:

@Entity - Used to mark a class as a Mapped class/Persistence class. This class must
have a no-arg constructor with package visibility so that hibernate can create an instance
of the Persistent class by the newInstance() method.

@Table - Used to specify the table details that used to persist the entity in the database.
If the name of the database table differs from the name of the class, the name attribute
should be used.

@Id - Used to mark the field as a primary key column. Annotating multiple fields with
@Id will make them composite keys

@GeneratedValue - Used to instruct the database to generate a value for the field automatically,
and to provide a strategy for doing so.

@Column - Maps the field to the table column. The @Column annotation has attributes listed below:

name - used to specify the name of the column. By default, it's assumed the column name and
variable name match. This attribute is required if that is not the case.
length - used to specify the size of the String value
nullable - used to mark the column as NOT NULL when the schema is generated.
unique - used to mark the column as UNIQUE to contain unique values.

