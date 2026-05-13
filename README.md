Kody - SpringBoot

springboot_3 : Hospital Application (Mappings, criteria api)
springboot_4 : Exception Handling
springboot_5 : Blog Application

Rest Api Guidelines 
1 Client server arch
2 stateless : server will not store any data
3 Cacheable
4 layered system
5 Uniform Interface
6 Code on Demand

REST Concepts

resource 
anything we want to expose to outside world through application

sub resource
one which is dependent on resource 
ex comments are sub resource of resource post
return laptop of student 12
Method : http://localhost:8282/resource/{id}/sub-resource

uri - uniform resource identifier
identifying resource

http methods 
get post put delete 

http response code : indicates status of completion of http req

============================================================================================================
# Kody-Springboot
SpringBoot Projects

Alt + ← / →         → Navigate back/forward
Ctrl + P            → Quick file search
Ctrl + Shift + O    → Search method in file

Ctrl + /            → Comment line
Ctrl + Shift + K    → Delete line

Ctrl + F5           → Run app

=====================================================================================================================
public class RegexConstants {

    public static final String NAME = "^[A-Za-z ]{2,50}$";
    public static final String EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    public static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,20}$";
    public static final String MOBILE = "^[6-9]\\d{9}$";

}
======================================================================================================================
Github

Here’s your **clean, no-confusion Git workflow** for working between your classroom PC and personal PC.

This is the exact routine you should follow every day.

---

# 🚀 FINAL CLEAN GIT FLOW

## 🔁 1. START OF WORK (IMPORTANT)

Always do this first on ANY PC:

```bash id="s1"
git pull origin main
```

👉 This ensures you have latest code from GitHub

---

## 💻 2. DO YOUR CODING

* Edit files in Visual Studio Code
* SAVE your work (Ctrl + S)

---

## 🔍 3. CHECK WHAT YOU CHANGED

```bash id="s2"
git status
```

---

## 📦 4. STAGE CHANGES

### Option A (simple, recommended)

```bash id="s3"
git add .
```

### Option B (safe/precise)

```bash id="s4"
git add path/to/file.java
```

---

## 💾 5. COMMIT CHANGES

```bash id="s5"
git commit -m "Clear message about what you did"
```

Example:

```bash id="s6"
git commit -m "Added appointment test and fixed insurance service"
```

---

## 🚀 6. PUSH TO GITHUB

```bash id="s7"
git push origin main
```

---

# 🔄 DAILY RULE (VERY IMPORTANT)

### BEFORE WORK:

```bash id="s8"
git pull origin main
```

### AFTER WORK:

```bash id="s9"
git add .
git commit -m "message"
git push origin main
```

---

# ⚠️ GOLDEN RULES (NEVER FORGET)

### ❌ Don’t do:

* git pull with unsaved work
* commit without checking `git status`
* switch PCs without pushing

---

### ✅ Always:

* Save files first (Ctrl + S)
* Run `git status` before commit
* Pull before starting work

---

# 🧠 SIMPLE MENTAL MODEL

```
Pull → Work → Add → Commit → Push → Switch PC → Pull again
```

---

# 💡 OPTIONAL (PRO LEVEL UPGRADE LATER)

When you're ready, I can upgrade you to:

* `git branch` (feature-based development)
* `git rebase` (clean history)
* `git stash` (save work temporarily)
* resolving merge conflicts properly

---

If you follow this flow strictly, you’ll never lose code or face confusion again.

