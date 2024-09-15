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

## Single Table Inheritance
- Single Table Inheritance artinya menyimpan seluruh Entity untuk relasi IS-A dalam satu table
- Artinya semua kolom di Entity akan digabung dalam satu table
- Kelebihan dari strategy ini adalah mudah dan cepat, tidak butuh melakukan JOIN
- Kekurangannya adalah harus membuat semua kolom menjadi nullable, karena tiap record hanya memiliki dari Entity
### Parent Entity
- Saat membuat Entity untuk IS-A Relationship, harus membuat parent Entity nya terlebih dahulu
- Parent Entity berisikan attribute yang tersedia di semua Child Entity nya
- Harus menyebutkan Strategy inheritance, menggunakan annotation `@Inheritance(strategy = InheritanceType.SINGLE_TABLE)`
### Child Entity
- Untuk Child Entity harus extends class Parent Entity nya
- `@DiscriminatorColumn` untuk memberi tahu JPA kolom mana yg menampung tipe Entity nya, ini di tambahkan di Entity Parentnya
- `@DiscriminatorValue` untuk menentukan value tipe Entity dari sebuah Entity

## Joined Table Inheritance
- Tiap Child Entity memiliki table masing-masing, namun akan melakukan join primary key dengan table Parent Entity
- Tidak perlu menggunakan Discriminator Column lagi, karena datanya sudah terpisah table
- Harus menyebutkan Strategy inheritance pada Parentnya, menggunakan annotation `@Inheritance(strategy = InheritanceType.JOINED)`

## Table Per Class Inheritance
- Tiap Entity akan dibuatkan table masing-masing, artinya Parent Entity dan Child Entity akan memiliki table masing-masing
- Strategi ini mirip seperti JOIN, namun tiap table menyimpan full kolom, sehingga tidak perlu JOIN
- Perlu diperhatikan, saat melakukan find menggunakan Parent Entity, akan sangat lambat karena harus SELECT from SELECT
- Harus menyebutkan Strategy inheritance pada Parentnya, menggunakan annotation `@Inheritance(strategy = InheritanceType.TABLEE_PER_CLASS)`

## Mapped Superclass
- Pada kasus OOP, biasanya membuat Parent Class sebagai class yang berisikan attribute-attribute yang sama untuk di gunakan di setiap Class yg extend
- Pada kasus Entity, bisa membuat Parent Class juga yang berisi attribute-attribute yg bisa digunakan di Entity lain, perlu menambahkan annotation `@MappedSuperclass` untuk memberi tahu ini hanya sebuah Parent Class tetapi bukan IS-A Relationship

## Locking
- Locking adalah aksi untuk mencegah data berubah dalam jeda waktu saat data sedang dibaca atau sedang digunakan
- Terdapat dua jenis Locking, `Optimistic` dan `Pessimistic`
- `Optimistic Locking` adalah proses multiple transaksi, dimana tiap transaksi tidak melakukan lock terhadap data, namun sebelum melakukan commit, tiap transaksi akan mengecek terlebih dahulu apakah data sudah berubah atau belum, jika sudah berubah karena transaksi lain, maka transaksi tersebut akan di rollback
- `Optimistic Locking` lebih cepat karena tidak perlu melakukan lock data, namun akan sering melakukan rollback jika ternyata data yg dicommit sudah berubah
- Saat menggunakan `Optimistic Locking` harus membuat version column sebagai penanda perubahan yang terjadi di data, bisa berupa version, Number (Integer, Short, Long) dan Timestamp, menggunakan annotation `@Version`, akan diisi otomatis oleh JPA setiap ada perubahan data

- `Pessimistic Locking` adalah proses multiple transaksi, dimana tiap transaksi akan melakukan locking terhadap data yang digunakan, sehingga tiap transaksi harus menunggu data yang akan digunakan jika data tersebut sedang di lock oleh transaksi lain
- Saat menggunakan `Pessimistic Locking`, perlu menentukan LockModeType nya, dengan menambahkan parameter di `entityManager` saat melakukan manipulasi data, contohnya `entityManager.find(Brand.class, "1", LockModeType.PESSIMISTIC_WRITE)`
- `NONE` tidak ada lock, `READ / OPTMISITIC` versi entity akan dicek di akhir transaksi sama seperti Optimistic Locking, `WRITE / OPTIMISTIC_FORCE_INCREMENT` versi entity akan dinaikkan secara otomatis walaupun data tidak di update
- `PESSIMISTIC_FORCE_INCREMENT` entity akan di lock secara pessimistic dan versi akan dinaikkan walau data tidak di update
- `PESSIMISTIC_READ` entity akan di lock secara pessimistic menggunakan shared lock (jika database mendukung), SELECT FOR SHARE
- `PESSIMISTIC_WRITE` entity akan di lock secara explicit, SELECT FOR UPDATE

## Managed Entity
- Saat membuat Object Entity baru (contohnya `new Brand()`) ini adalah Unmanaged Entity
- Ketika Unmanaged Entity disimpan ke database menggunakan EntityManager, secara otomatis objectnya akan berubah menjadi Managed Entity
- Setiap perubahan yg terjadi pada Managed Entity, secara otomatis akan diupdate ke database ketika melakukan commit, walaupun tidak melakukan update/merge secara manual
- Untuk merubah Managed Entity menjadi Unmanaged Entity, menggunakan `entityManager.detach(objectEntityNya)`
- Life cycle Managed Entity hanya terjadi di dalam transaksi, jika transaksi sudah di commit atau di close, maka semua Managed Entity akan berubah menjadi Unmanaged Entity

## Schema Generator
- JPA memiliki fitur untuk membuat tabel otomatis dari Class Entity nya
- Namun ini sangat tidak disarankan, karena akan sulit untuk tracking perubahan schema database nya, cocok untuk testing saja
- Lebih baik disarankan menggunakan database versioning sepert `flywaydb`
- Untuk mengatur fitur Hibernate Schema Generator, bisa menggunakan property `jakarta.persistence.schema-generation.database.action` di file `persistence.xml` dengan value:
- `none` tidak melakukan apapun
- `create` membuat schema
- `drop` menghapus schema
- `drop-and-create` menghapus dan membuat schema

## JPA Query Language (JPA QL/JPQL)
- Untuk melakukan query ke database, JPA memiliki standarisasi Query Language, jadi tidak menggunakan SQL yang spesifik ke database yang digunakan, karena dengan JPA bisa berganti-ganti database yg digunakan
- Saat menggunakan JPA QL, object yg dihasilkan adalah object dari class `Query` atau `TypedQuery<T>`, mirip seperti PreparedStatement, dimana bisa menambahkan parameter jika JPA QL yang dibuat membutuhkan parameter
- Jika melakukan query yang sudah jelas Entity nya, sangat disarankan menggunakan `TypedQuery<T>`
- Untuk NativeQuery hanya bisa menggunakan `Query` sebagai hasil return nya, tambahkan annotation `@SuppressWarnings("unchecked")` untuk menghilangkan warning
- Untuk melakukan SELECT, tidak menggunakan nama kolom, tetapi menggunakan nama attribute, dan untuk SELECT all tidak menggunakan *, tetapi menggunakan alias nya saja

## Named Query
- JPA memiliki fitur Named Query, digunakan untuk membuat alias untuk JPA QL yg dibuat
- JPA QL cukup dibuat sekali dan memberikan alias nya, sehingga QL bisa digunakan berkali-kali hanya dengan menyebutkan nama aliasnya
- Named Query biasanya di tempatkan di Entity Class, menggunakan annotation `@NamedQuery` atau `@NamedQueries` jika lebih dari satu Named Query
- Contohnya `@NamedQueries({ @NamedQuery(name = "aliasnya", query = "querynya") })`
- Saat membuat alias nya, sebaiknya diikuti dengan nama Entity nya, agar tidak bentrok dengan alias dari Entity lain, karena Named Query bersifat global

## Non Query
- JPA QL bisa digunakan untuk membuat perintah Update dan Delete, dengan membuat query seperti biasa
- Untuk execute query nya menggunakan method `query.executeUpdate()`
- Bisa digunakan untuk JPA QL maupun Native Query
- Namun untuk Update dan Delete tetap disarankan menggunakan `merge` dan `remove`, karena fitur optimistic locking tidak akan berjalan


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
- test/InheritanceTest.java
- test/LockingTest.java
- test/ManagedEntityTest.java
- test/JpaQueryLanguageTest.java
