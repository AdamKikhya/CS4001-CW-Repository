# CS4001 — Java Gadget Shop

A Java Swing GUI application built for the CS4001 Programming coursework at London Metropolitan University. The program simulates a gadget shop where you can add mobile phones and MP3 players to a list, make calls, download music, and manage your inventory through a graphical interface.

---

## What It Does

The app lets you:

- Add **Mobile phones** and **MP3 players** to a shop inventory
- View all gadgets in the list with their full details
- Make a phone call on a Mobile (deducts calling credit)
- Download music onto an MP3 (uses up available memory)
- Delete a gadget from the inventory
- Clear all input fields in one click

All output (gadget details, call results, error messages) prints to the terminal/console.

---

## Project Structure

```
GadgetShop/
├── Gadget.java        — Base class for all gadgets
├── Mobile.java        — Subclass of Gadget, adds calling credit
├── MP3.java           — Subclass of Gadget, adds available memory
├── GadgetShop.java    — Main GUI class, runs the application
└── package.bluej      — BlueJ project file (open this folder in BlueJ)
```

---

## The Classes

### Gadget
The parent class. Every gadget has four attributes:
- `model` — the name/model of the device (String)
- `price` — price in pounds (double)
- `weight` — weight in grams (int)
- `size` — physical dimensions, e.g. "71mm x 137mm x 9mm" (String)

It has a getter for each attribute and a `display()` method that prints everything to the console.

---

### Mobile (extends Gadget)
Represents a mobile phone. Adds one extra attribute:
- `callingCredit` — available minutes of calling credit (int)

Extra methods:
- `getCallingCredit()` — returns remaining credit
- `addCredit(int amount)` — tops up credit (must be positive)
- `makeCall(String phoneNumber, int duration)` — makes a call if enough credit exists, otherwise prints an error
- `display()` — calls Gadget's display, then also prints the calling credit

---

### MP3 (extends Gadget)
Represents an MP3 player. Adds one extra attribute:
- `availableMemory` — storage space in MB (int)

Extra methods:
- `getAvailableMemory()` — returns remaining memory
- `downloadMusic(int memoryRequired)` — downloads music if enough memory exists, otherwise prints an error
- `deleteMusic(int memoryFreed)` — frees up memory by deleting music
- `display()` — calls Gadget's display, then also prints the available memory

---

### GadgetShop
The main class — this is where the GUI lives. It extends `JFrame` and implements `ActionListener`.

It holds an `ArrayList<Gadget>` that stores all gadgets added during the session.

**Text fields (10 total):**

| Field | Purpose |
|---|---|
| Model | Gadget model name |
| Price | Price in pounds |
| Weight | Weight in grams |
| Size | Physical dimensions |
| Credit (min) | Calling credit — Mobile only |
| Memory (MB) | Available memory — MP3 only |
| Phone No | Number to call |
| Duration (min) | Call length |
| Download (MB) | Size of music to download |
| Item No | Index of gadget to act on |

**Buttons (7 total):**

| Button | What it does |
|---|---|
| Add Mobile | Creates a Mobile from the input fields and adds it to the list |
| Add MP3 | Creates an MP3 from the input fields and adds it to the list |
| Clear | Wipes all 10 text fields |
| Display All | Prints every gadget in the list to the console with its index |
| Make A Call | Calls `makeCall()` on the Mobile at the given index |
| Download Music | Calls `downloadMusic()` on the MP3 at the given index |
| Delete Gadget | Removes the gadget at the given index from the list |

---

## Input Validation

The **Item No** field is validated with a `try/catch` block:
- If the input isn't a number → dialog: *"Please enter a valid integer"*
- If the number is out of range → dialog: *"Number out of range"*
- Returns `-1` if invalid — all buttons that use it check for `-1` before doing anything

---

## How to Run

### In BlueJ
1. Open BlueJ
2. Go to **Project → Open Project**
3. Navigate to this folder and click **Open**
4. Click **Compile**
5. Right-click **GadgetShop** → **void main(String[] args)** → OK

### From the Command Line
Make sure you're in the project folder, then:
```bash
javac GadgetShop.java
java GadgetShop
```

---

## GUI Design

The interface uses a dark theme with colour-coded buttons:

- **Green** — Add Mobile, Add MP3
- **Red** — Clear
**Cyan/Blue** — Display All
- **Orange** — Make A Call, Download Music
- **Purple** — Delete Gadget

Built with Java Swing using `GridBagLayout` for the input area and `FlowLayout` for the button row. Custom fonts (Segoe UI), styled text fields with dark backgrounds, and a header bar with the app title.

---

## Notes

- Gadgets are stored in memory only — they reset when the app is closed
- The `Item No` field uses **zero-based indexing** (first gadget = 0, second = 1, etc.)
- `Display All` prints to the BlueJ terminal or command prompt, not inside the GUI window
- The `Delete Gadget` button is an additional feature beyond the base spec

---

## Author

**Adam Kikhya**
London Metropolitan University — CS4001 Programming
