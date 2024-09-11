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
- Pastikan menggunakan Tipe Data Non Primitif (Object), karena tipe data di Tabel bisa null

## Tipe Data Enum
- Jika memiliki Class Entity dengan antribute tipe data Enum, harus memberitahu JPA cara menyimpan Enum tersebut di database
- Dengan menggunakan annotation `@Enumerated(EnumType.STRING atau EnumType.ORDINAL)`
- JPA menyediakan dua strategy untuk menyimpan data Enum, bisa dalam bentuk Integer/Ordinal atau dalam String, disarankan menggunakan strategy String
- Jika menggunakan String, nama value dari enum akan di simpan sebagai String di database, jika menggunakan Ordinal, value dari enum akan disimpan sebagai Integer sesuai dengan index dari value di enum

## Tipe Data Date and Time
- Date and Time (Date, Timestamp, Calendar, LocalDate, LocalDateTime, LocalTime, Instant dll)
- Jika menggunakan `java.util.Date`, disarankan menggunakan annotation `@Temporal(TemporalType)` untuk memberi tahu JPA tipe data yang digunakan, karena `java.util.Date` bisa untuk Date, Time dan Timestamp

## Tipe Data Large Object
- Terdapat dua jenis Large Object di database, Character Large Object (menampung data text besar) dan Binary Large Object (menampung tipe data Binary besar, seperti gambar, vidio, dll)
- `java.sql.Clob` Representasi Large Object di JDBC untuk character large object
- `java.sql.Blob` Representasi Large Object di JDBC untuk binary large object
- Menggunakan `Clob` dan `Blob` cukup menyulitkan karena harus membaca data menggunakan Java IO
- Di JPA agar lebih mudah bisa menggunakan `String atau char[]` untuk character large object
- Dan `byte[]` untuk Binary Large Object
- Harus menambahkan annotation `@Lob`

## Transient
- Semua attribute di Class Entity secara default akan dianggap sebagai kolom di Table
- Jika ingin membuat attribute di Class Entity, namun tidak dianggap sebagai kolom di Table, bisa gunakan annotation `@Transient`

## Embedded
- Di JPA ada sebuah fitur Embedded, untuk menambahkan attribute berupa Class lain, namun tetap mapping ke table yang sama
- Untuk menandai sebuah Class sebagai Class Embedded yg bisa digunakan sebagai attribute di Class Entity (Embedded Attribute), maka harus menambahkan annotation `@Embeddable` di Class Embedded nya, dan annotation `@Embedded` di attribute Class Entity nya
- Kemudian isi attribute pada Class Embedded tersebut, bisa tambahkan annotation `@Column` seperti biasa
- Bisa dikatakan fitur Embedded ini berguna untuk melakukan grouping beberapa attribute column, kemudian disimpan di dalam Class Embedded terpisah

## Embedded ID
- Jika ingin membuat attribute Id (pirmary key) lebih dari satu attribute di Class Entity, maka harus menggunakan Class Embedded
- Dan untuk menandai bahwa attribute tersebut adalah Id, menggunakan annotation `@EmbeddedId`
- Khusus untuk Class Embedded untuk primary key, direkomendasikan implements Serializable

## Collection
- Ketika membuat attribute dalam bentuk Collection, ini merupakan representasi dari relasi OneToMany pada Table
- Untuk kasus yg sangat sederhana, bisa membuat attribute dengan tipe collection yang secara otomatis menggunakan Table Join untuk mengambil datanya
- Attribute Collection harus ditandai dengan annotation `@ElementCollection`
- Untuk menentukan table mana yg digunakan untuk menyimpan Collection, menggunakan annotation `@CollectionTable`
- Untuk menentukan kolom mana yg akan diambil datanya di Table Collection, menggunakan annotaion `@Column` seperti biasa
- Khusus untuk Collection `Map<K, V>`, harus menambahkan annotation `@MapKeyColumn` untuk memberi tahu JPA kolom mana yg menjadi key, dan `@Column` untuk kolom mana yg menjadi value
- Perlu diperhatikan, Table yang digunakan untuk menyimpan Collection tidak bisa memiliki relasi ke table lain lagi, karena ketika akan melakukan update data, Hibernate akan menghapus data lama, baru dibuat data baru yg di update, sehingga jika ada foreign key dengan Tabel lain, akan terjadi error

## Entity Listener
- JPA memiliki fitur entity Listener, digunakan untuk membuat sebuah Class Listener, yg nantinya akan dipangiil oleh JPA ketika sebuah operasi terjadi terhadap Entity nya
- Contoh ketika setiap data diubah, kolom updated_at akan diisi dengan waktu saat ini
- Macam-macam event yg bisa di Listen:
- `@PrePersist` (Dieksekusi sebelum melakukan persist/insert entity)
- `@PostPersist` (Dieksekusi setelah melakukan persist/insert entity)
- `@PreRemove` (Dieksekusi sebelum melakukan delete entity)
- `@PostRemove` (Dieksekusi setelah melakukan delete entity)
- `@PreUpdate` (Dieksekusi sebelum melakukan update entity)
- `@PostUpdate` (Dieksekusi setelah melakukan update entity)
- `@PostLoad` (Dieksekusi setelah melakukan load entity)
- Kemudian menambahkan annotation `@EntityListeners(ClassListenerNya.Class)` pada Class Entity untuk melisten event, dan akan mengeksekusi Class Listener
- Perlu membuat interface untuk di implement di Class Entity dan dijadikan parameter di method yang akan dieksekusi di Class Listener
- Jika tidak ingin membuat Class Listener, bisa langsung menambahkan listener langsung di dalam Class Entity, namun kekurangannya, Listener tidak bisa digunakan di Class Entity lain

## One to One Relationship
- Merupkan relasi dimana satu tabel berelasi dengan satu data di tabel lain
- Ada beberapa cara melakukan relasi, dengan Foreign Key atau dengan Primary Key yang sama
- JPA mendukung relasi menggunakan Foreign Key ataupun Primary Key yang sama
- Untuk menambahkan attribut di Entity yg berelasi dengan Entity lain, menggunakan annotation `@OneToOne`
- Untuk memberi tahu JPA tentang kolom mana yang digunakan untuk melakukan JOIN Foreign Key, menggunakan annotation `@JoinColumn(name = "kolomForeignKey", referencedColumnName = "referenceKolomIdDiEntityLain")`, anotasi ini diletakkan di Entity yg memiliki Foreign Key Kolom, dan Entity relasi cukup tambahkan `@OneToOne(mappedBy = "namaAttributeForeignDiEntityLain")`
- Jika JOIN menggunakan Primary Key yang sama, menggunakan annotation `@PrimaryKeyJoinColumn(name = "kolomPrimaryKeyDiEntitiyIni", referencedColumnName = "kolomPrimaryKeyDiEntityLain")` 
- Untuk attribute di Entity lain yg menjadi relasi, menambahkan annotation `@OneToOne(mappedBy = "namaAttributeForeignDiEntityLain")`

## One To Many Relationship
- Untuk membuat atribut di Entity memiliki relasi OneToMany dengan Entity lain, menggunakan `@OneToMany(mappedBy = "namaAttributeForeignDiEntityLain")`
- Atribut di Entity tipe datanya harus dibungkus dalam collection, seperti `List<T>` atau `Set<T>`
- Relasi OneToMany jika dilihat dari Entity sebaliknya adalah relasi ManyToOne, menggunakan `@ManyToOne` dan harus menambahkan `@JoinColumn(name = "kolomForeignKey", referencedColumnName = "referenceKolomIdDiEntityLain")`

## Many To Many Relationship
- Pada relasi ManyToMany, butuh tabel tambahan ditengah sebagai jembatan, oleh karena itu, menggunakan `@JoinTable`
- Untuk table yg ditengah sebagai jembatan, tidak butuh membuat Class Entity nya
- Untuk menambahkan atribut yg memiliki relasi ManyToMany, menggunakan `@ManyToMany` dan `@JoinTable`
- Untuk atrbut pada Class Entity sebaliknya, cukup gunakan `@OneToMany(mappedBy = "namaAttributeForeignDiEntityLain")`

## Fetch
- Secara default, beberapa jenis relasi memiliki value fetch EAGER, artinya saat melakukan find Entity, akan otomatis JOIN, walaupun tidak membutuhkan relasinya
- Kebalikan dari EAGER adalah LAZY, dimana artinya relasi akan di QUERY secara terpisah (tidak di JOIN) ketika memanggil attributenya saja, jika tidak, maka tidak akan di QUERY
- `OneToOne` default fetchnya EAGER
- `OneToMany` default fetchnya LAZY
- `ManyToOne` default fetchnya EAGER
- `ManyToMany` default fetchnya LAZY
- Untuk mengubah nilai fetch dari relasi, dengan menambahkan attribute `fetch = FetchType.TIPENYA` pada anotasi relasinya

## IS-A Relationship
- IS-A biasanya digunakan untuk mendukung konsep pewarisan di relational database, yg notabanenya tidak mendukung koonsep ini
- Contohnya struktur table Employee, memiliki detail Manager, VicePresident, Supervisor, dll
- IS-A Relationship jika dalam OOP, maka implementasinya adalah berupa Inheritance/Pewarisan
- IS-A memiliki beberapa cara impementasi (strategy) di table nya, yaitu
- `Single Table Inheritance`
- `Joined Table Inheritance`
- `Table Per Class Inheritance`

## Parent Entity
- Saat membuat Entity untuk IS-A Relationship, harus membuat parent Entity nya terlebih dahulu
- Parent Entity berisikan attribute yang tersedia di semua Child Entity nya
- Harus menyebutkan Strategy inheritance, menggunakan annotation `@Inheritance(strategy = InheritanceType.TIPENYA)`

## Child Entity
- Untuk Child Entity harus extends class Parent Entity nya
- `@DiscriminatorColumn` untuk memberi tahu JPA kolom mana yg menampung tipe Entity nya, ini di tambahkan di Entity Parentnya
- `@DiscriminatorValue` untuk menentukan value tipe Entity dari sebuah Entity

## Single Table Inheritance
- Single Table Inheritance artinya menyimpan seluruh Entity untuk relasi IS-A dalam satu table
- Artinya semua kolom di Entity akan digabung dalam satu table
- Kelebihan dari strategy ini adalah mudah dan cepat, tidak butuh melakukan JOIN
- Kekurangannya adalah harus membuat semua kolom menjadi nullable, karena tiap record hanya memiliki dari Entity

## Learning
- test/EntityManagerFacotryTest.java
- test/EntityManagerTest.java
- test/TransactionTest.java
- test/CrudTest.java
- test/ColumnTest.java
- test/GeneratedValueTest.java
- test/DataTypeTest.java
- test/EnumTest.java
- test/DateTest.java
- test/LargeObjectTest.java
- test/EmbeddedTest.java
- test/CollectionTest.java
- test/EntityListenerTest.java
- test/EntityRelationshipTest.java
