# Serialization

[![Site](https://img.shields.io/badge/jkid-github-lightgrey)](https://github.com/yole/jkid)     
The Kotlin(or Java) Serialization code is created by referring to the `jkid` library.

## Table of content

- [Structure](#structure)
    - [Json Object](#json-object-structure)
    - [Json Array](#json-array-structure)
    - [Json Data Types](#json-data-types-structure)
- [Serialize Process](#serialize-process)

## <a id="structure"> Structure

* ## <a id="json-object-structure"> Json Object

    <img src="drawio/json-object-structure/json-object-structure.svg"  width="1200">

* ## <a id="json-array-structure"> Json Array

    <img src="drawio/json-array-structure/json-array-structure.svg"  width="1200">

* ## <a id="json-data-types-structure"> Json Data Types

  | Data Type | Json String |
  | --- | --- |
  | String | `"`FRAGILE`"` |
  | Number | 1234 |
  | Object | [`Json Object`](#json-object-structure) |
  | Array | [`Json Array`](#json-array-structure) |
  | Boolean | true/false |
  | Null | null |

## <a id="serialize-process"> Serialize Process

(📂 `fragile/src/main/java/kr/pokeum/fragile/serialization/Serializer.kt`)

<img src="drawio/fragile-serialize.svg"  width="550">