# 🎯 نظام الاختبارات التفاعلي - Java Quiz System

![Java](https://img.shields.io/badge/Java-Swing-blue)
![SQLite](https://img.shields.io/badge/Database-SQLite-green)
![License](https://img.shields.io/badge/License-MIT-yellow)

## 📖 نظرة عامة
نظام اختبارات تفاعلي متكامل بلغة Java يدعم الواجهة العربية بالكامل. يتميز بواجهة مستخدم جذابة، إدارة كاملة للأسئلة، ومؤقت زمني للاختبارات.

## ✨ الميزات الرئيسية
- 🎯 **واجهة عربية كاملة** - تصميم مخصص للغة العربية
- ⏱️ **مؤقت زمني** - 15 ثانية لكل سؤال
- 🆘 **نظام مساعدة** - خاصية 50-50
- 📊 **إدارة الأسئلة** - إضافة، حذف، تعديل الأسئلة
- 💾 **قاعدة بيانات** - SQLite للتخزين المحلي
- 🎨 **تصميم حديث** - أزرار ونصوص مدورة

## 🗂️ هيكل المشروع

src/
  ├── application/ # User Interfaces
  │   ├── Login.java # Login Screen
  │   ├── Rules.java # Rules Screen
  │   ├── Quiz.java # Quiz Screen
  │   ├── Score.java # Results Screen
  │   └── QuestionManagerArt.java # Control Panel
  └── database/ # Data Management
      ├── DBConnection.java # Database Connection
      ├── QuestionDAO.java # Data Operations
      └── QuestionTableInit.java # Table Initialization






## 🚀 التشغيل والتثبيت

### المتطلبات الأساسية
- Java JDK 8 أو أحدث
- مكتبة SQLite JDBC (موجودة في مجلد libs)

### خطوات التشغيل
1. **تحميل المشروع:**
   ```bash
   git clone https://github.com/devhammam/java-quiz-system.git
   cd java-quiz-system



# التجميع
javac -cp ".;libs/sqlite-jdbc-3.36.0.3.jar" src/application/Login.java

# التشغيل
java -cp ".;libs/sqlite-jdbc-3.36.0.3.jar" src.application.Login



🎮 كيفية الاستخدام
تشغيل البرنامج: java Login

1.إدخال اسم المستخدم

2.قراءة القواعد والبدء في الاختبار

3.الإجابة على الأسئلة مع المراقبة الزمنية

4.عرض النتائج النهائية والإحصائيات



👨‍💻 المطور
همام معين رسام

📧 البريد الإلكتروني: hammamrassam31@gmail.com

💼 متخصص في هندسة البرمجيات و الأمن السيبراني والذكاء الاصطناعي

🌐 ملفي الشخصي على GitHub

📄 الترخيص
هذا المشروع مرخص تحت MIT License.

🤝 المساهمات
المساهمات مرحب بها! يمكنك:

الإبلاغ عن الأخطاء

اقتراح ميزات جديدة


تطوير وتحسين الكود
