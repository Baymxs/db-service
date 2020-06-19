# Database service
---
Database service is a Java program that provides a service for working with data in a database. This service, based on input parameters (command line arguments), type of operation and input file, extracts the necessary data from the database and generates the processing result in the output file.

# Installation
---
- Build the project by Maven (mvn clean compile assembly:single)
- [Download](https://drive.google.com/file/d/1_g4ljuvsWxLQjW3JxB6wclENlb4jR94L/view?usp=sharing) Jar File (`dbservice-1.0-SNAPSHOT-jar-with-dependencies`)
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
{
   "personPurchases":[
      {
         "person":{
            "lastName":"Байрамов",
            "name":"Нижад"
         },
         "purchases":{
            "Курина приправа":13,
            "Хлеб":27,
            "Мюсли":220,
            "Гречневая крупа":56,
            "Консервированные ананасы":97,
            "Молоко":94,
            "Куриное филе":440,
            "Кефир":37
         },
         "totalExpense":984
      },
      {
         "person":{
            "lastName":"Караваев",
            "name":"Григорий"
         },
         "purchases":{
            "Гречневая крупа":112,
            "Консервированные ананасы":97,
            "Молоко":94,
            "Куриное филе":440
         },
         "totalExpense":743
      },
      {
         "person":{
            "lastName":"Караваев",
            "name":"Антон"
         },
         "purchases":{
            "Курина приправа":13,
            "Хлеб":27,
            "Мюсли":110,
            "Кефир":37
         },
         "totalExpense":187
      },
      {
         "person":{
            "lastName":"Онищук",
            "name":"Дмитрий"
         },
         "purchases":{
            "Курина приправа":13,
            "Хлеб":27,
            "Консервированные ананасы":97,
            "Куриное филе":220
         },
         "totalExpense":357
      },
      {
         "person":{
            "lastName":"Синицын",
            "name":"Данил"
         },
         "purchases":{
            "Хлеб":27,
            "Мюсли":110,
            "Гречневая крупа":56,
            "Молоко":47
         },
         "totalExpense":240
      }
   ],
   "totalExpense":2511,
   "avgExpense":502.2,
   "type":"stat"
}
```
# License
----
MIT

----
> GitHub: [@baymxs](https://github.com/Baymxs) 
VK: [@baymxs](https://vk.com/endecv)

