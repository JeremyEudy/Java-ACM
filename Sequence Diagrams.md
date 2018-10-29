# Sequence Diagrams
```sequence
Title: ACM Project
Switch->Print: Input 1
Switch->Add Subject: Input 2
Switch->Add Object: Input 3
Switch->Delete Subject: Input 4
Switch->Delete Object: Input 5
Switch->Add Role: Input 6
Switch->Delete Role: Input 7
Switch->Quit: Input 0
```
```sequence
Title: Class Interaction
ACMTest.java->ACM.java: Create/Manipulate ACM
ACM.java->ACMObject.java: Create/Manipulate Subjects/Objects
ACM.java->ACMTest.java: Return ArrayList of Subjects/Objects
```
# Function Breakdown
## Print ACM
```flowchart
st=>start
e=>end
print=>operation: printACM()
st->print(right)->print
```
## Add Subject
```flowchart
st=>start
e=>end
roleList=>operation: Get Role List
name=>inputoutput: Get Name
ID=>subroutine: Generate ID
rolePrint=>subroutine: Print Roles
role=>inputoutput: Assign Role
subject=>operation: Add Subject
attrib=>parallel: Generate Attributes
objType=>subroutine: objType = 1

st->roleList
roleList->attrib(path1, right)->name->ID->objType->rolePrint(left)->role
attrib(path2, bottom)->role->subject->e
```
## Add Object
```flowchart
st=>start
e=>end
attrib=>parallel: Generate Attributes
name=>inputoutput: Get Name
ID=>subroutine: Generate ID
hierarchy=>inputoutput: Get hierarchy
objType=>subroutine: objType = 0
object=>operation: Add Object

st->attrib(path1, right)->name->ID->objType(left)->hierarchy
attrib(path2, bottom)->hierarchy->object->e
```
## Remove Subject
```flowchart
st=>start
e=>end
ID=>inputoutput: Get ID
clearance=>condition: Verify Access
delete=>operation: Delete Subject
fail=>operation: Rejected Clearance

st->ID->clearance(yes, bottom)->delete->e
clearance(no, right)->fail->e
```
## Remove Object
```flowchart
st=>start
e=>end
ID=>inputoutput: Get ID
clearance=>condition: Verify Access
delete=>operation: Delete Object
fail=>operation: Rejected Clearance

st->ID->clearance(yes, bottom)->delete->e
clearance(no, right)->fail->e
```
## Add Role
```flowchart
st=>start
e=>end
rolePrint=>operation: List Roles
getHierarchy=>inputoutput: Get Role Position
addRole=>operation: Add Role

st->rolePrint->getHierarchy->addRole->e
```
## Remove Role
```flowchart
st=>start
e=>end
rolePrint=>operation: List Roles
roleID=>inputoutput: Get Role ID
clearance=>condition: Verify Access
delete=>operation: Delete Role
fail=>operation: Rejected Clearance

st->rolePrint->roleID->clearance(yes, bottom)->delete->e
clearance(no, right)->fail->e
```