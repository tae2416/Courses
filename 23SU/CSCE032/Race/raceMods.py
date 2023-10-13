# Track object
class Track:
    def __init__(self, shape, distance, gates): 
        self.shape = shape          # Shape of the track in str
        self.distance = distance    # Straight distance
        self.gates = gates          # Number of gates 

    def show(self):
        print(f'Track:')
        print(f'\t Shape:  {self.shape}')
        print(f'\t Distance:  {self.distance} m')
        print(f'\t Gates:  {self.gates}')
        print('')


# Racer object
class Racer:
    def __init__(self, rType, rAge, rSpeed, rExp, rGate): 
        self.rType = rType          # Racer type (e.g. dog, cat, horse,...)
        self.rAge = rAge            # Racer age
        self.rSpeed = rSpeed        # Racer max speed
        self.rExp = rExp            # Racer Exp
        self.rGate = rGate          # Racer's gate number

    def show(self):
        print(f'Racer:')
        print(f'\t Type:  {self.rType}')
        print(f'\t Age:  {self.rAge}')
        print(f'\t Speed:  {self.rSpeed}')
        print(f'\t Experience:  {self.rExp}')
        print(f'\t Gate #:  {self.rGate}')
        print('')

# Generate racers
    # Type of racers (e.g. dogs, horses, etc.)
    # Stamina (based on age, )
    # Speed (based on age, )
    # Expertice (based on age, talent, intelligence, # of races)