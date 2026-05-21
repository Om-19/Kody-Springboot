Kody - SpringBoot

springboot_3 : Hospital Application (Mappings, criteria api)
springboot_4 : Exception Handling
springboot_5 : Blog Application
springboot_6 : Employee Task
springboot_7 : Product Task
springboot_8 : Online Learning Platform


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

Ctrl + P            → Quick file search
Ctrl + Shift + O    → Search method in file

=====================================================================================================================
public class RegexConstants {

    public static final String NAME = "^[A-Za-z ]{2,50}$";
    public static final String EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    public static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,20}$";
    public static final String MOBILE = "^[6-9]\\d{9}$";

}
======================================================================================================================
