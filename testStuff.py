class Dog():
    """ Dogs are the best"""
    # No attributes yet
    def __init__(self, color:str = None, age:int = 0):
        self.color = color
        self.age = age

    # Only 1 method
    def fetch(self, toy:str = 'ball') -> str:
        return toy
    
    

Cass = Dog()
print(Cass.color)
Cass.color = 'black'
print(Cass.color)
print(Cass.age)
Cass.age = 2
print(Cass.age)

returned_toy = Cass.fetch()
print(returned_toy)
real_return = Cass.fetch('stick')
print(real_return)
