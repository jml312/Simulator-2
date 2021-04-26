import matplotlib.pyplot as plt

labels = ['P1', 'P2', 'P3', 'P4', 'P5']
ids = ['0', '1', '2', '3', '4']
lastTime = [0.0, 0.0, 0.0, 0.0, 0.0]
eatingTimes = [0, 0, 0, 0, 0]
thinkingTimes = [0, 0, 0, 0, 0]
waitingTimes = [0, 0, 0, 0, 0]
droppingTimes = [0, 0, 0, 0, 0]
width = 0.35
lastTimeTemp = 0.0
philosopher0Times = []
j = 0

fig, ax = plt.subplots()

with open("DeadlockRecovery_py.txt") as file:
  for line in file:
    line = [x.strip() for x in line.split("|")]
    length = len(line)
    if length != 1 and length == 3:
      philosopher, action, time = line
      if action != "DEADLOCK":
        philosopher = str(philosopher.split(" ")[1])
        time = (float(time.split(":")[2]) - 21.364) if (float(time.split(
            ":")[2]) - 21.364) > -0.001 else (float(time.split(":")[2]) - 21.364 + 60)
        for i in ids:
          if (philosopher == i and time != 0):
            j = int(i)
            lastTimeTemp = time
            time = time - lastTime[j]
            lastTime[j] = lastTimeTemp
            #print("LastTime: ", lastTime)
            if action == "is hungry":
              thinkingTimes[j] = round(thinkingTimes[j] + time, 2)
            if action == "finished eating":
              eatingTimes[j] = round(eatingTimes[j] + time, 2)
            if action == "is eating":
              waitingTimes[j] = round(waitingTimes[j] + time, 2)
            if action == "is thinking":
              droppingTimes[j] = round(droppingTimes[j] + time, 2)


# print(eatingTimes)
# print(thinkingTimes)
# print(waitingTimes)
# print((eatingTimes[0] + thinkingTimes[0] + droppingTimes[0] + waitingTimes[0]))
# print((eatingTimes[1] + thinkingTimes[1] + droppingTimes[1] + waitingTimes[1]))
# print((eatingTimes[2] + thinkingTimes[2] + droppingTimes[2] + waitingTimes[2]))
# print((eatingTimes[3] + thinkingTimes[3] + droppingTimes[3] + waitingTimes[3]))
# print((eatingTimes[4] + thinkingTimes[4] + droppingTimes[4] + waitingTimes[4]))
        
ax.bar(labels, eatingTimes, width, label='Eating')
ax.bar(labels, thinkingTimes, width, label='Thinking')
ax.bar(labels, droppingTimes, width, label='Dropping')
ax.bar(labels, waitingTimes, width, label='Waiting')

ax.set_ylabel('Time (s)')
ax.set_title('Time Spent Doing Each Task by Philosopher')
ax.legend()

plt.show()

