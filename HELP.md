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

## Learning
- test/EntityManagerFacotryTest.java
- test/EntityManagerTest.java
