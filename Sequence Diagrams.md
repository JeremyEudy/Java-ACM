# Sequence Diagrams
```sequence
Title: ACM Project
Switch->Print: Input 1
Switch->Add Subject: Input 2
Switch->Delete Subject: Input 4
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