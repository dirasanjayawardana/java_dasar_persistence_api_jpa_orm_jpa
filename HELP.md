# Java Persistence API (JPA)
- Object Relational Mapping (ORM) adalah teknik pemetaan (mapping) antara database dengan Object pada bahasa pemrograman
- Dengan ORM bisa melakukan manipulasi data di database menggunakan Object di bahasa pemrogramannya tanpa harus query SQL manual
- Java Persistence API (JPA) adalah salah satu standardarisai untuk Object Relational Mapping di Java

## Diagram JPA
- Kode java --> JPA (Spesifikasi/Interface) --> JPA Provider (Implementasi) --> JDBC --> JDBC Driver --> Database

## JPA Provider
- JPA merupakan spesifikasi untuk ORM di Java, harus menggunakan implementasinya atau JPA Provider
- Mirip seperti JDBC yang memerlukan JDBC Driver sebagai implementasinya
- Contoh JPA Provider: Hibernate ORM, OpenJPA, Eclipse Link

## Entity
- Merupakan Class yang merepresentasikan table di database
- Harus menambahkan annotation `@Entity` pada class
- Class Entity merupakan Java Bean (class yg memiliki getter setter)
- Wajib memiliki default constructor (no arguments contructor), agar JPA bisa membuat object baru tanpa parameter ketika melakukan mapping data dari table ke object Entity
- Entity wajib memiliki primary key (Id) dengan memberikan `@Id` pada attribute di Class
- Secara default nama table di database sama dengan nama Class Entity, jika ingin nama table dan class Entity berbeda, bisa menggunakan `@Table(name = "nama_table")` pada Class Entity

## Entity Manager Factory
- Saat menggunakan JPA, hal pertama yg harus dibuat adalah object EntityManagerFactory, digunakan untuk melakukan management EntityManager
- Ini seperti Datasource (ConnectionPool) yg digunakan untuk melakukan management Connection, hanya dibuat sekali saja dalam aplikasi
- EntityManagerFactory akan membaca Entity Class dan mengaktifkan fitur-fitur Entity dan konfigurasi JPA saat pertama kali dibuat
- EntityManagerFactory adalah interface, untuk membuat objectnya menggunakan Hibernate
- Sebelum membuat EntityManagerFactory, harus membuat konfigurasi JPA, dalam file `META-INF/persitence.xml`
- Untuk membuat EntityManagerFactory, menggunakan `Persistence.createEntityManagerFactory("namaPersistenceUnit")` dari package `jakarta.persistence.Persistence`

## Entity Manager
- EntityManager mirip seperti Connection di JDBC, dimana ketika ingin berinteraksi dengan database, akan menggunakan EntityManager
- EntityManager dibuat ketika butuh berinteraksi dengan database, dan setelah selesai perlu untuk menutupnya `close()`
- untuk membuat EntityManager, menggunakan `entityManagerFactory.createEntityManager()`

## Entity Transaction
- Saat menggunakan JDBC, secara default operasi ke DB adalah auto commit, sehingga tidak perlu membuat Transaction
- Namun di JPA, secara default harus menggunakan database transaction saat melakukan operasi manipulasi data entity
- Transaction di JPA di representasikan dalam interface `EntityTransaction`
- Untuk membuat EntityTransaction dengan menggunakan `entityManager.getTransaction()`
- Method dalam EntityTransaction, `begin()`, `commit()`, `rollback()`, `getRollbackOnly()`, `setRollbackOnly()`

## CRUD
- Untuk melakukan proses CRUD (Create, Read, Update, Delete) ke database, bisa menggunan `EntityManager`
- Method untuk CRUD pada EntityManager
- `persist(entity)` untuk menyimpan entity
- `merge(entity)` untuk mengupdate entity
- `remove(entity)` untuk menghapus entity
- `find(Class, id)` untuk mendapatkan entity berdasarkan entity

## Column
- Secara default, nama attribute di Class Entity akan dimapping sebagai nama Kolom di Table
- Untuk custom mapping nama Kolom di attribute Class, menggunakan annotasi `@Column`

## Generated Value
- Beberapa database memiliki fitur membuat Primary Key secara otomatis, sehingga JPA tidak bisa mengubah Id nya, karena dibuat oleh database
- Untuk menandai bahwa Id dibuat secara otomatis oleh database, menggunakan annnotation `@GeneratedValue(strategy = GenerationType.TipePrimaryKeyNya)`

## Tipe Data
- Pastikan tipe data di Class entity sama dengan tipe data di Table. tipe data yg didukung oleh JPA
- Semua Number (Byte, Short, Integer, Long, Float, Double)
- Semua Big Number (BigInteger, BigDecimal)
- Boolean
- String dan Charracter

## Learning
- test/EntityManagerFacotryTest.java
- test/EntityManagerTest.java
- test/TransactionTest.java
- test/CrudTest.java
- test/ColumnTest.java
- test/GeneratedValueTest.java
