import raceMods

# Main program for Racing
def main():
    track = raceMods.Track('Straight', 100, 8)
    racer1 = raceMods.Racer('Dog', 2, 10, 10, 1)

    track.show()
    racer1.show()

if __name__ == '__main__':
    main()


# Generate track
    # Number of gates
    # Curved / straight

# Generate racers
    # Type of racers (e.g. dogs, horses, etc.)
    # Stamina (based on age, )
    # Speed (based on age, )
    # Expertice (based on age, talent, intelligence, # of races)

# Execute the race
    # Determine racer conditions for the day (i.e. max performance level for the day)
        # Add variation for beginning, middle, and end (i.e. modifier for performance)
            # Delayed start
    # Add in potential for disqualification
    # Calculated complete time
        # Output ranking

