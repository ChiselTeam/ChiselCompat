# Chisel Compat

Supported integrations: Allthemodium, Applied Energistics 2, Create, Dyenamics,
Forbidden Arcanus, Thaumcraft, Neovitae, Occultism, and generic modded materials.
Provider IDs are maintained in `CompatFamilies.java`; common tags alone cannot
identify providers before block registration. Adding another provider there does
not require a dependency on that mod's classes.

## Credits and license

GPL-3.0-only; see [LICENSE](LICENSE). Sources and model-generation helpers
were adapted from Chisel commit `7916696b9389371e6045e871a4b24710bf59dbbc`
on its `26.3` branch.
Original Chisel authors: Cricket, Joshy, and the Chisel contributors.
Chisel credits textures from the Painterly pack, a stone variation inspired by
Doku, asie's 1.7.2 port, and artwork by Drullkus, Cebularrz, and PorotoMz.
