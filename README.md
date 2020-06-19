# Database service
---
Database service is a Java program that provides a service for working with data in a database. This service, based on input parameters (command line arguments), type of operation and input file, extracts the necessary data from the database and generates the processing result in the output file.

# Installation
---
- Build the project yourself
- [Download](https://drive.google.com/file/d/1R2GtZ-10nYxeZtzFh1tsYy33FPydwUaK/view?usp=sharing) Jar File (`java -jar 
filesmergesort-1.0-SNAPSHOT.jar`)
# Usage
---
### Flags
- ##### Database operation name
  * With `-o` 
- ##### Input file name
  * With `--in` <file_name>
  * With `--input` <file_name>
- ##### Output file name
  * With `--out` <file_name>
  * With `--output` <file_name>
- ##### Information about program options
  * With `-h` 
  * With `--help`
# Example
---
#### Search operation
```
-o search --input input.json --output output.json
```
`input.json`
```json
{
   "lastNames":[
      "Байрамов",
      "Караваев",
      "Голиков",
      "Онищук"
   ],
   "productCounts":[
      {
         "name":"Хлеб",
         "count":5
      },
      {
         "name":"Творог",
         "count":10
      },
      {
         "name":"Молоко",
         "count":2
      },
      {
         "name":"Кефир",
         "count":2
      }
   ],
   "minMaxPrice":[
      {
         "min":1,
         "max":1000
      },
      {
         "min":1,
         "max":800
      },
      {
         "min":1,
         "max":600
      },
      {
         "min":1,
         "max":200
      }
   ],
   "badCustomers":[
      1,
      2,
      3,
      4
   ]
}
```
`output.json`
```json
{
   "personsWithLastNameCriteria":[
      {
         "lastNameCriteria":"Байрамов",
         "persons":[
            {
               "lastName":"Байрамов",
               "name":"Нижад"
            }
         ]
      },
      {
         "lastNameCriteria":"Караваев",
         "persons":[
            {
               "lastName":"Караваев",
               "name":"Григорий"
            },
            {
               "lastName":"Караваев",
               "name":"Антон"
            }
         ]
      },
      {
         "lastNameCriteria":"Голиков",
         "persons":[

         ]
      },
      {
         "lastNameCriteria":"Онищук",
         "persons":[
            {
               "lastName":"Онищук",
               "name":"Дмитрий"
            }
         ]
      }
   ],
   "personsWithProductAndCountCriteria":[
      {
         "productCount":{
            "name":"Хлеб",
            "count":5
         },
         "persons":[

         ]
      },
      {
         "productCount":{
            "name":"Творог",
            "count":10
         },
         "persons":[

         ]
      },
      {
         "productCount":{
            "name":"Молоко",
            "count":2
         },
         "persons":[
            {
               "lastName":"Байрамов",
               "name":"Нижад"
            },
            {
               "lastName":"Караваев",
               "name":"Григорий"
            }
         ]
      },
      {
         "productCount":{
            "name":"Кефир",
            "count":2
         },
         "persons":[

         ]
      }
   ],
   "personsWithMinMaxPriceCriteria":[
      {
         "minMaxPriceCriteria":{
            "min":1,
            "max":1000
         },
         "persons":[
            {
               "lastName":"Синицын",
               "name":"Данил"
            },
            {
               "lastName":"Онищук",
               "name":"Дмитрий"
            },
            {
               "lastName":"Караваев",
               "name":"Григорий"
            },
            {
               "lastName":"Байрамов",
               "name":"Нижад"
            },
            {
               "lastName":"Караваев",
               "name":"Антон"
            }
         ]
      },
      {
         "minMaxPriceCriteria":{
            "min":1,
            "max":800
         },
         "persons":[
            {
               "lastName":"Синицын",
               "name":"Данил"
            },
            {
               "lastName":"Онищук",
               "name":"Дмитрий"
            },
            {
               "lastName":"Караваев",
               "name":"Григорий"
            },
            {
               "lastName":"Караваев",
               "name":"Антон"
            }
         ]
      },
      {
         "minMaxPriceCriteria":{
            "min":1,
            "max":600
         },
         "persons":[
            {
               "lastName":"Синицын",
               "name":"Данил"
            },
            {
               "lastName":"Онищук",
               "name":"Дмитрий"
            },
            {
               "lastName":"Караваев",
               "name":"Антон"
            }
         ]
      },
      {
         "minMaxPriceCriteria":{
            "min":1,
            "max":200
         },
         "persons":[
            {
               "lastName":"Караваев",
               "name":"Антон"
            }
         ]
      }
   ],
   "personsWithBadCustomersCountCriteria":[
      {
         "badCustomersCountCriteria":1,
         "persons":[
            {
               "lastName":"Синицын",
               "name":"Данил"
            }
         ]
      },
      {
         "badCustomersCountCriteria":2,
         "persons":[
            {
               "lastName":"Онищук",
               "name":"Дмитрий"
            },
            {
               "lastName":"Синицын",
               "name":"Данил"
            }
         ]
      },
      {
         "badCustomersCountCriteria":3,
         "persons":[
            {
               "lastName":"Синицын",
               "name":"Данил"
            },
            {
               "lastName":"Онищук",
               "name":"Дмитрий"
            },
            {
               "lastName":"Караваев",
               "name":"Антон"
            }
         ]
      },
      {
         "badCustomersCountCriteria":4,
         "persons":[
            {
               "lastName":"Синицын",
               "name":"Данил"
            },
            {
               "lastName":"Онищук",
               "name":"Дмитрий"
            },
            {
               "lastName":"Караваев",
               "name":"Антон"
            },
            {
               "lastName":"Караваев",
               "name":"Григорий"
            }
         ]
      }
   ],
   "type":"search"
}
```
#### Stat operation
```
-o stat --input input.json --output output.json
```
`input.json`
```json
{
   "startDate":"Jun 01, 2020 9:57:10 AM",
   "endDate":"Jun 09, 2020 9:57:10 AM"
}
```

`output.json`
```json
```
# License
----
MIT

----
> GitHub: [@baymxs](https://github.com/Baymxs) 
VK: [@baymxs](https://vk.com/endecv)

